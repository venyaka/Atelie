package veniamin.backend.atelie.controller;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import veniamin.backend.atelie.constant.PathConstants;
import veniamin.backend.atelie.dto.request.RegisterReqDTO;
import veniamin.backend.atelie.dto.request.UserAuthorizeReqDTO;
import veniamin.backend.atelie.dto.response.TokenRespDTO;
import veniamin.backend.atelie.service.AuthorizeService;

@Controller
@RequiredArgsConstructor
@RequestMapping(PathConstants.AUTHORIZE_CONTROLLER_PATH)
public class AuthorizeController {

    private final AuthorizeService authorizeService;

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // без .html — Thymeleaf сам его найдёт в папке templates
    }

    @PostMapping("/login")
    public String dashboardPage(HttpServletRequest request,
                                Model model) {
        try {
            return "redirect:/dashboard";
        } catch (Exception e) {
            model.addAttribute("error", "Ошибка подтверждения: " + e.getMessage());
            return "login";
        }
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("registerDTO", new RegisterReqDTO());
        return "register"; // templates/register.html
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("registerDTO") @Valid RegisterReqDTO registerDTO,
                               HttpServletRequest request,
                               Model model) {
        try {
            authorizeService.registerUser(registerDTO, request);
            return "redirect:/authorize/login?registered";
        } catch (Exception e) {
            model.addAttribute("error", "Ошибка регистрации: " + e.getMessage());
            return "register";
        }
    }

    @GetMapping("/verificate")
    public String verificationForm() {
        return "verification"; // templates/verification.html
    }

    @PostMapping("/verificateCode")
    public String sendVerificationCode(@RequestParam String email,
                                       HttpServletRequest request,
                                       Model model) {
        authorizeService.sendVerificationCode(email, request);
        model.addAttribute("message", "Код подтверждения отправлен на почту.");
        return "verification";
    }

    @RequestMapping(value = "/verification", method = { RequestMethod.GET, RequestMethod.POST })
    public String verificateUser(@RequestParam String email,
                                 @RequestParam String token,
                                 HttpServletRequest request,
                                 Model model) {
        try {
            authorizeService.verificateUser(email, token);
            return "redirect:/dashboard";
        } catch (Exception e) {
            model.addAttribute("error", "Ошибка подтверждения: " + e.getMessage());
            return "verification";
        }
    }
}
