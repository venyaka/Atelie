package veniamin.backend.atelie.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import veniamin.backend.atelie.entity.Cash;
import veniamin.backend.atelie.service.impl.CashServiceImpl;

@Controller
@RequestMapping("/cash")
@RequiredArgsConstructor
public class CashController {
    private final CashServiceImpl service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("cash", service.getAll());
        return "cash-list";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("cashItem", new Cash());
        return "cash-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Cash entity) {
        service.save(entity);
        return "redirect:/cash";
    }
}