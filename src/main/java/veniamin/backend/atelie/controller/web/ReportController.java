package veniamin.backend.atelie.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import veniamin.backend.atelie.dto.CompletedOrderDto;
import veniamin.backend.atelie.dto.FinancialReportDto;
import veniamin.backend.atelie.entity.Role;
import veniamin.backend.atelie.entity.User;
import veniamin.backend.atelie.exception.NotFoundException;
import veniamin.backend.atelie.exception.errors.NotFoundError;
import veniamin.backend.atelie.repository.UserRepository;
import veniamin.backend.atelie.service.impl.ReportServiceImpl;
import veniamin.backend.atelie.service.impl.UserServiceImpl;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manager/reports")
public class ReportController {

    private final ReportServiceImpl reportService;
    private final UserRepository userRepository;

    @GetMapping
    public String showReportsPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<Role> roles = (List<Role>) auth.getAuthorities();

        if (roles.contains(Role.ROLE_MANAGER)) {
//            model.addAttribute("completedOrders", reportService.getCompletedOrdersByCutter(1L)); // или без параметра
//            model.addAttribute("financialReport", reportService.getFinancialReport());
            model.addAttribute("cutterOrders", reportService.getCurrentMonthCutterOrders());
        } else if (roles.contains(Role.ROLE_CUTTER)) {
            model.addAttribute("completedOrders", reportService.getCompletedOrdersByCutter(userRepository.findByEmail(auth.getName()).get().getId()));
        }

        model.addAttribute("roles", roles);

        return "reports";
    }

    @GetMapping("/completed")
    public String completedOrders(@RequestParam("cutterId") Long cutterId,
                                  Model model,
                                  Authentication authentication) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Optional<User> optionalUser = userRepository.findByEmail(auth.getName());
        if (optionalUser.isEmpty()) {
            throw new NotFoundException(NotFoundError.USER_NOT_FOUND);
        }
        User user = optionalUser.get();

        List<Role> roles = (List<Role>) auth.getAuthorities();
        // CUTTER может смотреть только свои
        if (!roles.contains(Role.ROLE_CUTTER) && !user.getId().equals(cutterId)) {
            return "redirect:/manager/reports?error=unauthorized";
        }

        List<CompletedOrderDto> completed = reportService.getCompletedOrdersByCutter(cutterId);
        model.addAttribute("completedOrders", completed);

        if (roles.contains(Role.ROLE_MANAGER)) {
            model.addAttribute("monthOrders", reportService.getCurrentMonthCutterOrders());
        }

        return "reports";
    }

    @GetMapping("/financial")
    public String financialReport(@RequestParam("day") int day,
                                  Model model,
                                  Authentication authentication) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<Role> roles = (List<Role>) auth.getAuthorities();

        if (!roles.contains(Role.ROLE_MANAGER)) {
            return "redirect:/manager/reports?error=unauthorized";
        }

        List<FinancialReportDto> financialReports = reportService.getFinancialReport(day);
        model.addAttribute("financialReports", financialReports);
        model.addAttribute("monthOrders", reportService.getCurrentMonthCutterOrders());

        return "reports";
    }


}
