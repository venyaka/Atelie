package veniamin.backend.atelie.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import veniamin.backend.atelie.entity.CostOrder;
import veniamin.backend.atelie.service.impl.CostOrderServiceImpl;

import java.util.Optional;

@Controller
@RequestMapping("/cost-orders")
@RequiredArgsConstructor
public class CostOrderController {
    private final CostOrderServiceImpl service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("costOrders", service.getAll());
        return "cost-order-list";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("costOrder", new CostOrder());
        return "cost-order-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute CostOrder entity) {
        service.save(entity);
        return "redirect:/cost-orders";
    }
}