//package veniamin.backend.atelie.controller.web;
//
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/dashboard")
//public class TableManagementController {
//
//    @GetMapping("/tables")
//    @PreAuthorize("isAuthenticated()")
//    public String showTables(Model model) {
//        // В зависимости от роли добавляем в модель допустимые действия
//        if (hasRole("ADMIN")) {
//            model.addAttribute("canSelect", true);
//            model.addAttribute("canInsert", false);
//            model.addAttribute("canUpdate", false);
//            model.addAttribute("canDelete", false);
//            model.addAttribute("canAdmin", true);
//        } else if (hasRole("MANAGER")) {
//            model.addAttribute("canSelect", true);
//            model.addAttribute("canInsert", true);
//            model.addAttribute("canUpdate", true);
//            model.addAttribute("canDelete", true);
//            model.addAttribute("canAdmin", false);
//        } else if (hasRole("TAILOR")) {
//            model.addAttribute("canSelect", true);
//            model.addAttribute("canInsert", true);
//            model.addAttribute("canUpdate", true);
//            model.addAttribute("canDelete", true);
//            model.addAttribute("canAdmin", false);
//        } else if (hasRole("CUSTOMER")) {
//            model.addAttribute("canSelect", true);
//            model.addAttribute("canInsert", true);
//            model.addAttribute("canUpdate", true);
//            model.addAttribute("canDelete", true);
//            model.addAttribute("canAdmin", false);
//        }
//
//        return "tables3"; // Thymeleaf-шаблон tables.html
//    }
//
//    private boolean hasRole(String role) {
//        return org.springframework.security.core.context.SecurityContextHolder.getContext()
//                .getAuthentication()
//                .getAuthorities()
//                .stream()
//                .anyMatch(a -> a.getAuthority().equals("ROLE_" + role));
//    }
//}

