package veniamin.backend.atelie.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import veniamin.backend.atelie.entity.Order;
import veniamin.backend.atelie.service.OrderService;
import veniamin.backend.atelie.service.impl.OrderServiceImpl;

import java.util.Optional;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderServiceImpl service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("orders", service.getAll());
        return "order-list";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("order", new Order());
        return "order-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Order entity) {
        service.save(entity);
        return "redirect:/orders";
    }
}