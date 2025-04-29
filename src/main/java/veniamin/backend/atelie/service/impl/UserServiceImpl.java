package veniamin.backend.atelie.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import veniamin.backend.atelie.exception.NotFoundException;
import veniamin.backend.atelie.exception.errors.NotFoundError;
import veniamin.backend.atelie.filter.jwt.JwtUtils;
import veniamin.backend.atelie.repository.UserRepository;
import veniamin.backend.atelie.service.UserService;
import veniamin.backend.atelie.utils.LogsUtils;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final MailServiceImpl mailService;

    private final LogsUtils logsUtils;

//    private final FileServiceImpl fileService;

    private final JwtUtils jwtUtils;

    private final SessionServiceImpl sessionService;
//
//    private final UserToUserRespDTO userToUserRespDTOMapper;
//
//    private final UserCreateReqDTOToUser userCreateReqDTOToUserMapper;
//
//    private final UserUpdateReqDTOToUser userUpdateReqDTOToUserMapper;
//
//    private final FileRepository fileRepository;

//    private static final Logger loggerUsers = LoggerFactory.getLogger("usersLogger");
//    private static final Logger loggerAuth = LoggerFactory.getLogger("authLogger");
//    private static final Logger loggerFile = LoggerFactory.getLogger("fileLogger");


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername " + "email " + email);
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new NotFoundException(NotFoundError.USER_NOT_FOUND));
    }

//    @Override
//    @Transactional
//    public UserRespDTO getCurrentUserInfo() {
//        User user = this.getCurrentUser();
//        UserRespDTO dto = getResponseDTO(user);
//        File avatar = user.getProfileAvatar();
//        dto.setAvatarUrl(avatar.getRemoteUrl());
//        logsUtils.log(loggerFile, "Get current user info");
//        return dto;
//    }
//
//    @Override
//    @Transactional
//    public UserRespDTO updateCurrentUser(UpdateCurrentUserReqDTO updateCurrentUserReqDTO, MultipartFile avatar) {
//        User user = this.getCurrentUser();
//        File file = user.getProfileAvatar();
//
//        if (avatar != null) {
//            FileDTO newAvatarDTO = fileService.save(avatar);
//            File newAvatar = fileService.findById(newAvatarDTO.getId());
//
//            if (!(FileType.IMAGE).equals(newAvatar.getFileType())) {
//                throw new BadRequestException(BadRequestError.FILE_FOR_AVATAR_MUST_BE_IMAGE);
//            }
//            if (newAvatar.getIsDeleted()) {
//                throw new BadRequestException(BadRequestError.FILE_DELETED);
//            }
//            user.setProfileAvatar(newAvatar);
//        } else if (updateCurrentUserReqDTO.getDeleteAvatar() != null && updateCurrentUserReqDTO.getDeleteAvatar()) {
//            user.setProfileAvatar(null);
//            file.setIsDeleted(true);
//            fileRepository.save(file);
//        }
//        user = userRepo.save(user);
//
//        if (user.getProfileAvatar() != null) {
//            fileService.setFileUsage(user, List.of(user.getProfileAvatar()));
//        }
//        if (updateCurrentUserReqDTO.getDeleteAvatar() != null && updateCurrentUserReqDTO.getDeleteAvatar()) {
//            user.setProfileAvatar(null);
//            fileService.setFileUsage(user, new ArrayList<>());
//        }
//        if ( updateCurrentUserReqDTO.getName() != null ) {
//            user.setName( updateCurrentUserReqDTO.getName() );
//        }
//        if ( updateCurrentUserReqDTO.getSurname() != null ) {
//            user.setSurname( updateCurrentUserReqDTO.getSurname() );
//        }
//        if ( updateCurrentUserReqDTO.getAboutMe() != null ) {
//            user.setAboutMe( updateCurrentUserReqDTO.getAboutMe() );
//        }
//        userRepo.save(user);
//
//        UserRespDTO resultDto = getResponseDTO(user);
//        if (user.getProfileAvatar() != null) {
//            resultDto.setAvatarUrl(user.getProfileAvatar().getRemoteUrl());
//        }
//        logsUtils.log(loggerUsers, "Update current userUpdateReqDTO");
//        return resultDto;
//    }
//
//    @Override
//    @Transactional
//    public void changePassword(ChangePasswordReqDTO changePasswordReqDTO) {
//        User user = this.getCurrentUser();
//        if (!passwordEncoder.matches(changePasswordReqDTO.getOldPassword(), user.getPassword())) {
//            throw new BadRequestException(BadRequestError.NOT_CORRECT_OLD_PASSWORD);
//        }
//        user.setPassword(passwordEncoder.encode(changePasswordReqDTO.getNewPassword()));
//        userRepo.save(user);
//
//        logsUtils.log(loggerUsers, "Change password");
//    }
//
//    @Override
//    @Transactional
//    public UserRespDTO getUserById(Long id) {
//        Optional<User> optionalUser = userRepo.findById(id);
//        if (optionalUser.isEmpty()) {
//            throw new NotFoundException(NotFoundError.USER_NOT_FOUND);
//        }
//        UserRespDTO dto = getResponseDTO(optionalUser.get());
//
//        logsUtils.log(loggerFile, "Get user (id " + id + ")");
//        return dto;
//    }
//
//    @Override
//    @Transactional
//    public ActionWithUserStatusResponseDTO deleteUserById(Long id) {
//        if (userRepo.findById(id).isEmpty()) {
//            throw new NotFoundException(NotFoundError.USER_NOT_FOUND);
//        }
//        userRepo.deleteById(id);
//
//        logsUtils.log(loggerUsers, "Delete user (id " + id + ")");
//        ActionWithUserStatusResponseDTO actionWithUserStatusResponseDTO = new ActionWithUserStatusResponseDTO();
//        actionWithUserStatusResponseDTO.setSuccefulMessage("Successful user deleted with id " + id);
//        return actionWithUserStatusResponseDTO;
//    }
//
//    @Override
//    @Transactional
//    public Page<UserRespDTO> getAllUsers(Pageable pageable) {
//        return userRepo.findAll(pageable).map(this::getResponseDTO);
//    }
//
//    @Override
//    @Transactional
//    public Page<User> getAllUsers(Specification<User> specification, Pageable pageable) {
//        return userRepo.findAll(specification, pageable);
//    }
//
//    @Override
//    @Transactional
//    public Page<UserRespDTO> getAllUserDTO(Specification<User> specification, Pageable pageable) {
//        Page<UserRespDTO> page = this.getAllUsers(specification, pageable).map(this::getResponseDTO);
//
//        logsUtils.log(loggerFile, "Get all users. Page number - " + pageable.getPageNumber() + " page size - " + pageable.getPageSize());
//        return page;
//    }
//
//    @Override
//    @Transactional
//    public UserRespDTO createUser(UserCreateReqDTO userCreateReqDTO) {
//        Optional<User> optionalUser = userRepo.findByEmail(userCreateReqDTO.getEmail());
//        if (optionalUser.isPresent()) {
//            throw new BadRequestException(BadRequestError.USER_ALREADY_EXISTS);
//        }
//        User user = userCreateReqDTOToUserMapper.sourceToDestination(userCreateReqDTO);
////        user.setPassword(passwordEncoder.encode("12345Qazdevelop"));
//        user.setIsEmailVerificated(Boolean.TRUE);
//        if ((null != userCreateReqDTO.getProfileAvatarId())) {
//            File newAvatar = fileService.findById(userCreateReqDTO.getProfileAvatarId());
//
//            if (!(FileType.IMAGE).equals(newAvatar.getFileType())) {
//                throw new BadRequestException(BadRequestError.FILE_FOR_AVATAR_MUST_BE_IMAGE);
//            }
//            if (newAvatar.getIsDeleted()) {
//                throw new BadRequestException(BadRequestError.FILE_DELETED);
//            }
//
//            user.setProfileAvatar(newAvatar);
//        }
//
//        if (null != userCreateReqDTO.getRoles() && user.getRoles().size() > 1) {
//            throw new BadRequestException(BadRequestError.LIST_OF_ROLE_SHOULD_CONTAINT_ONLY_ONE_ROLE);
//        }
//        if (null != userCreateReqDTO.getRoles() && !userCreateReqDTO.getRoles().isEmpty()) {
//            user.setRoles(userCreateReqDTO.getRoles().stream().collect(Collectors.toSet()));
//        } else {
//            user.setRoles(Set.of(Role.USER));
//        }
//
//        userRepo.save(user);
//        user.setIsEmailVerificated(Boolean.TRUE);
//        userRepo.save(user);
//
//        if (null != userCreateReqDTO.getProfileAvatarId()) {
//            fileService.setFileUsage(user, List.of(user.getProfileAvatar()));
//        }
//
//        logsUtils.log(loggerUsers, "Create user " + userCreateReqDTO.getEmail());
//        return getResponseDTO(user);
//    }
//
//    @Override
//    @Transactional
//    public UserRespDTO updateUser(UserUpdateReqDTO userUpdateReqDTO, Long userId) {
//        Optional<User> userOptional = userRepo.findById(userId);
//        if (userOptional.isEmpty()) {
//            throw new NotFoundException(NotFoundError.USER_NOT_FOUND);
//        }
//        User user = userOptional.get();
//        checkUserUpdateCorrectData(user, userUpdateReqDTO);
//        userUpdateReqDTOToUserMapper.sourceToDestination(userUpdateReqDTO, user);
//        if ((null != userUpdateReqDTO.getProfileAvatarId())) {
//            File newAvatar = fileService.findById(userUpdateReqDTO.getProfileAvatarId());
//            if (!(FileType.IMAGE).equals(newAvatar.getFileType())) {
//                throw new BadRequestException(BadRequestError.FILE_FOR_AVATAR_MUST_BE_IMAGE);
//            }
//            user.setProfileAvatar(newAvatar);
//            if (newAvatar.getIsDeleted()) {
//                throw new BadRequestException(BadRequestError.FILE_DELETED);
//            }
//        }
//        if (null != userUpdateReqDTO.getRoles() && userUpdateReqDTO.getRoles().size() > 1) {
//            throw new BadRequestException(BadRequestError.LIST_OF_ROLE_SHOULD_CONTAINT_ONLY_ONE_ROLE);
//        }
//        if (null != userUpdateReqDTO.getRoles() && !userUpdateReqDTO.getRoles().isEmpty()) {
//            user.setRoles(userUpdateReqDTO.getRoles().stream().collect(Collectors.toSet()));
//        }
//
//
//        if (null != userUpdateReqDTO.getProfileAvatarId()) {
//            fileService.setFileUsage(user, List.of(user.getProfileAvatar()));
//        }
//
//
//        userRepo.save(user);
//
//        logsUtils.log(loggerUsers, "Update user (id " + userId + ")");
//        return getResponseDTO(user);
//    }
//
//    @Override
//    @Transactional
//    public void logout() {
//        User user = this.getCurrentUser();
//        user.setRefreshToken(null);
//        userRepo.save(user);
//        sessionService.endOldSessions(user.getId());
//
//        logsUtils.log(loggerAuth, "Logout user");
//    }
//
//    @Override
//    @Transactional
//    public UserRespDTO changeUserStatus(Long userId, UserStatusChangeReqDTO userStatusChangeReqDTO) {
//        User user = userRepo.findById(userId).orElseThrow(() -> new NotFoundException(NotFoundError.USER_NOT_FOUND));
//        if (userStatusChangeReqDTO.getIsActive() != null) {
//            user.setIsActive(userStatusChangeReqDTO.getIsActive());
//        }
//        if (userStatusChangeReqDTO.getIsDeleted() != null) {
//            user.setIsDeleted(userStatusChangeReqDTO.getIsDeleted());
//        }
//        if (userStatusChangeReqDTO.getIsEmailVerificated() != null) {
//            user.setIsEmailVerificated(userStatusChangeReqDTO.getIsEmailVerificated());
//        }
//        user = userRepo.save(user);
//        logsUtils.log(loggerUsers, "Change user status (id " + userId + ")");
//        return getResponseDTO(user);
//    }
//
//    @Override
//    public List<Role> getRoles() {
//        return Arrays.stream(Role.values()).collect(Collectors.toList());
//    }
//
//    @Override
//    public UserRespDTO getResponseDTO(User user) {
//        UserRespDTO userRespDTO = userToUserRespDTOMapper.sourceToDestination(user);
//        userRespDTO.setRoles(user.getRoles().stream().collect(Collectors.toList()));
//        if (null != user.getProfileAvatar()) {
//            userRespDTO.setProfileAvatarId(user.getProfileAvatar().getId());
//        }
//        return userRespDTO;
//    }
//
//    @Override
//    public UserFilter setCompanyNameToUserFilterIfCompanyAdmin(UserFilter userFilter) {
//        User user = this.getCurrentUser();
//        if (user.getRoles().contains(Role.COMPANY_ADMIN)) {
//            if (user.getCompany() == null) {
//                throw new BadRequestException(BadRequestError.CURRENT_USER_NOT_SETTED_COMPANY);
//            }
//            userFilter.setCompanyName(user.getCompany().getName());
//        }
//        return userFilter;
//    }
//
//    private String generateValidatingToken() {
//        return RandomStringUtils.randomAlphanumeric(50);
//    }
//
//    private void checkUserUpdateCorrectData(User user, UserUpdateReqDTO userUpdateReqDTO) {
//        if (null != userUpdateReqDTO.getRoles() && userUpdateReqDTO.getRoles().isEmpty()) {
//            throw new BadRequestException(BadRequestError.NOT_CORRECT_REQUEST_BODY_DATA);
//        }
//    }
//
//    private User getCurrentUser(){
//        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
//        return userRepo.findByEmail(userEmail).get();
//    }

}
