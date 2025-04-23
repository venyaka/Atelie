package veniamin.backend.atelie.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import veniamin.backend.atelie.entity.ComplicationOrder;
import veniamin.backend.atelie.service.impl.ComplicationOrderServiceImpl;

@Controller
@RequestMapping("/complication-orders")
@RequiredArgsConstructor
public class ComplicationOrderController {
    private final ComplicationOrderServiceImpl service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("complicationOrders", service.getAll());
        return "complication-order-list";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("complicationOrder", new ComplicationOrder());
        return "complication-order-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute ComplicationOrder entity) {
        service.save(entity);
        return "redirect:/complication-orders";
    }
}