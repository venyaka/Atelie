package veniamin.backend.atelie.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import veniamin.backend.atelie.dto.request.RegisterReqDTO;
import veniamin.backend.atelie.dto.request.UserAuthorizeReqDTO;
import veniamin.backend.atelie.dto.response.TokenRespDTO;

public interface AuthorizeService {

    ResponseEntity<TokenRespDTO> authorizeUser(UserAuthorizeReqDTO userAuthorizeDTO);

    void registerUser(@Valid RegisterReqDTO registerDTO, HttpServletRequest request);

    void sendVerificationCode(String email, HttpServletRequest request);

    void verificateUser(String email, String verificationToken);
}
