package veniamin.backend.atelie.controller.rest;

import veniamin.backend.atelie.constant.PathConstants;
import veniamin.backend.atelie.dto.request.RegisterReqDTO;
import veniamin.backend.atelie.dto.request.UserAuthorizeReqDTO;
import veniamin.backend.atelie.dto.response.TokenRespDTO;
import veniamin.backend.atelie.service.AuthorizeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(PathConstants.AUTHORIZE_CONTROLLER_PATH)
public class AuthorizeRestController {

    private final AuthorizeService authorizeService;


    @PostMapping("/login")
    public ResponseEntity<TokenRespDTO> authorizeUser(@Valid @RequestBody UserAuthorizeReqDTO userAuthorizeDTO) {
        return authorizeService.authorizeUser(userAuthorizeDTO);
    }

//    @PostMapping("/register")
//    public void registerUser(@Valid @RequestBody RegisterReqDTO registerDTO, HttpServletRequest request) {
//        authorizeService.registerUser(registerDTO, request);
//    }

    @PostMapping("/verificateCode")
    public void sendVerificationCode(@RequestParam String email, HttpServletRequest request) {
        authorizeService.sendVerificationCode(email, request);
    }
//
//    @PostMapping("/verification")
//    public void verificateUser(@RequestParam(required = true) String email,
//                               @RequestParam(required = true) String verificationToken,
//                               HttpServletRequest request) {
//        authorizeService.verificateUser(email, verificationToken);
//    }
}