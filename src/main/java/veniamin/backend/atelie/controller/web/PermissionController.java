package veniamin.backend.atelie.controller.web;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import veniamin.backend.atelie.entity.Role;
import veniamin.backend.atelie.entity.User;
import veniamin.backend.atelie.service.impl.UserServiceImpl;

import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/admin/permissions")
@RequiredArgsConstructor
public class PermissionController {

    private final UserServiceImpl userService;

    @GetMapping
    public String getPermissionsPage(Model model) {
        List<User> users = userService.getAllUsers();
        List<Role> availableRoles = Arrays.asList(Role.values());
        List<String> allTables = userService.getAllTables();

        model.addAttribute("users", users);
        model.addAttribute("availableRoles", availableRoles);
        model.addAttribute("allTables", allTables);

        return "permissions"; // permissions.html
    }

    @PostMapping("/update")
    public String updatePermissions(@RequestParam Long userId,
                                    @RequestParam(required = false) List<Role> roles,
                                    @RequestParam(required = false) List<String> denies,
                                    HttpServletRequest request) {

        Set<Role> roleSet = roles != null ? new HashSet<>(roles) : new HashSet<>();
        Set<String> deniedTables = denies != null ? new HashSet<>(denies) : new HashSet<>();

        userService.updateUserRolesAndPermissions(userId, roleSet, deniedTables);

        return "redirect:/admin/permissions";
    }

}
