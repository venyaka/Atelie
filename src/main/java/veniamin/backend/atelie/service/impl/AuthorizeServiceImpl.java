package veniamin.backend.atelie.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import veniamin.backend.atelie.dto.request.RegisterReqDTO;
import veniamin.backend.atelie.dto.request.UserAuthorizeReqDTO;
import veniamin.backend.atelie.dto.response.TokenRespDTO;
import veniamin.backend.atelie.entity.User;
import veniamin.backend.atelie.exception.BadRequestException;
import veniamin.backend.atelie.exception.NotFoundException;
import veniamin.backend.atelie.exception.errors.AuthorizedError;
import veniamin.backend.atelie.exception.errors.BadRequestError;
import veniamin.backend.atelie.exception.errors.NotFoundError;
import veniamin.backend.atelie.filter.jwt.JwtUtils;
import veniamin.backend.atelie.repository.UserRepository;
import veniamin.backend.atelie.service.AuthorizeService;
import veniamin.backend.atelie.utils.LogsUtils;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorizeServiceImpl implements AuthorizeService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final MailServiceImpl mailService;

    private final LogsUtils logsUtils;

    private final SessionServiceImpl sessionService;

    private final JwtUtils jwtUtils;

    private static final Logger loggerAuth = LoggerFactory.getLogger("authLogger");


    @Override
    public ResponseEntity<TokenRespDTO> authorizeUser(UserAuthorizeReqDTO userAuthorizeDTO) {
        String userEmail = userAuthorizeDTO.getEmail();
        String userPassword = userAuthorizeDTO.getPassword();
        Optional<User> userOptional = userRepository.findByEmail(userEmail);

        if (userOptional.isEmpty()) {
            throw new BadCredentialsException(AuthorizedError.USER_WITH_THIS_EMAIL_NOT_FOUND.getMessage());
        }
        User user = userOptional.get();
        if (!passwordEncoder.matches(userPassword, user.getPassword())) {
            throw new BadCredentialsException(AuthorizedError.NOT_CORRECT_PASSWORD.getMessage());
        }
        checkUserCanAuthorize(user);
        user.setRefreshToken(jwtUtils.generateRandomSequence());
        String jwtToken = jwtUtils.generateToken(user);
        String refreshToken = jwtUtils.generateRefreshToken(user);
        userRepository.saveAndFlush(user);

        TokenRespDTO tokenDTO = new TokenRespDTO();
        tokenDTO.setAccessToken("Bearer " + jwtToken);
        tokenDTO.setRefreshToken("Bearer " + refreshToken);

        logsUtils.log(loggerAuth, "Authorize user - " + userEmail);


        return new ResponseEntity<>(tokenDTO, HttpStatus.OK);
    }

    @Override
    @Transactional
    public void registerUser(@Valid RegisterReqDTO registerDTO, HttpServletRequest request) {
        if (userRepository.findByEmail(registerDTO.getEmail()).isPresent()) {
            throw new BadRequestException(BadRequestError.USER_ALREADY_EXISTS);
        }
        User user = new User();
        user.setEmail(registerDTO.getEmail());
        user.setName(registerDTO.getName());
        user.setSurname(registerDTO.getSurname());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setIsEmailVerificated(Boolean.FALSE);
        user.setToken(generateValidatingToken());
        userRepository.save(user);

        mailService.sendUserVerificationMail(user, request);

        logsUtils.log(loggerAuth, "Register user - " + registerDTO.getEmail());
    }

    @Override
    public void sendVerificationCode(String email, HttpServletRequest request) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(NotFoundError.USER_NOT_FOUND));
        if (user.getIsEmailVerificated()) {
            throw new BadRequestException(BadRequestError.USER_ALREADY_VERIFICATED);
        }
        mailService.sendUserVerificationMail(user, request);

        logsUtils.log(loggerAuth, "Repeate send verificate code to email - " + user.getEmail());
    }

    @Override
    @Transactional
    public void verificateUser(String email, String verificationToken) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException(NotFoundError.USER_NOT_FOUND);
        }
        User user = optionalUser.get();

        if (user.getIsEmailVerificated()) {
            throw new BadRequestException(BadRequestError.USER_ALREADY_VERIFICATED);
        }

        if (null == user.getToken() || !user.getToken().equals(verificationToken)) {
            throw new BadRequestException(BadRequestError.NOT_CORRECT_VERIFICATION_CODE);
        }

        user.setToken(null);
        user.setIsEmailVerificated(Boolean.TRUE);
        userRepository.save(user);

        logsUtils.log(loggerAuth, "User verification - " + email);
    }


    private void checkUserCanAuthorize(User user) {
        if (user.getIsDeleted().equals(Boolean.TRUE)) {
            throw new AccessDeniedException(AuthorizedError.USER_IS_DELETED.getMessage());
        }
        if (!user.getIsEmailVerificated()) {
            throw new AccessDeniedException(AuthorizedError.USER_NOT_VERIFY.getMessage());
        }
        if (user.getIsActive().equals(Boolean.FALSE)) {
            throw new AccessDeniedException(AuthorizedError.USER_IS_NOT_ACTIVE.getMessage());
        }
    }

    private String generateValidatingToken() {
        return RandomStringUtils.randomAlphanumeric(50);
    }
}
