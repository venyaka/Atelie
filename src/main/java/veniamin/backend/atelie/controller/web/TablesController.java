package veniamin.backend.atelie.controller.web;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import veniamin.backend.atelie.entity.User;
import veniamin.backend.atelie.exception.NotFoundException;
import veniamin.backend.atelie.exception.errors.NotFoundError;
import veniamin.backend.atelie.repository.UserRepository;
import veniamin.backend.atelie.service.impl.TableServiceImpl;
import veniamin.backend.atelie.service.impl.UserServiceImpl;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/tables")
@RequiredArgsConstructor
public class TablesController {

    private final TableServiceImpl tableService;
    private final UserServiceImpl userService;
    private final UserRepository userRepository;

    @GetMapping
    public String viewTables(@RequestParam(required = false) String table, Model model) {
        if (table != null) {
            List<Map<String, Object>> items = tableService.getAll(table);
            List<String> fields = tableService.getFields(table);

            model.addAttribute("items", items);
            model.addAttribute("fields", fields);
        }
        model.addAttribute("table", table);
        return "table";
    }

    @PostMapping("/add")
    public String addRecord(@RequestParam Map<String, String> params) {
        String table = params.remove("table");
        tableService.addRecord(table, params);
        return "redirect:/tables?table=" + table;
    }

    @PostMapping("/edit")
    public String editRecord(@RequestParam Map<String, String> params) {
        String table = params.remove("table");
        String id = params.remove("id");
        tableService.editRecord(table, id, params);
        return "redirect:/tables?table=" + table;
    }

    @PostMapping("/delete")
    public String deleteRecord(@RequestParam String table, @RequestParam String id) {
        tableService.deleteRecord(table, id);
        return "redirect:/tables?table=" + table;
    }
}
