package veniamin.backend.atelie.controller.web;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import veniamin.backend.atelie.constant.PathConstants;
import veniamin.backend.atelie.dto.request.RegisterReqDTO;
import veniamin.backend.atelie.dto.request.UserAuthorizeReqDTO;
import veniamin.backend.atelie.service.AuthorizeService;

@Controller
@RequiredArgsConstructor
@RequestMapping(PathConstants.AUTHORIZE_CONTROLLER_PATH)
public class AuthorizeController {

    private final AuthorizeService authorizeService;

    @GetMapping("/login")
    public String login(Model model) {

        UserAuthorizeReqDTO authorizeReqDTO = new UserAuthorizeReqDTO();
        model.addAttribute("authorizeReqDTO", authorizeReqDTO);
        return "login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("registerDTO", new RegisterReqDTO());
        return "register";
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

    @RequestMapping(value = "/verification", method = { RequestMethod.GET, RequestMethod.POST })
    public String verificateUser(@RequestParam String email,
                                 @RequestParam String token,
                                 HttpServletRequest request,
                                 Model model) {
        try {
            authorizeService.verificateUser(email, token);
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("error", "Ошибка подтверждения: " + e.getMessage());
            return "verification";
        }
    }
}
