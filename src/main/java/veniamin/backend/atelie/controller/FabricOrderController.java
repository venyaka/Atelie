package veniamin.backend.atelie.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import veniamin.backend.atelie.entity.FabricOrder;
import veniamin.backend.atelie.service.FabricOrderService;
import veniamin.backend.atelie.service.impl.FabricOrderServiceImpl;

import java.util.Optional;

@Controller
@RequestMapping("/fabric-orders")
@RequiredArgsConstructor
public class FabricOrderController {
    private final FabricOrderServiceImpl service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("fabricOrders", service.getAll());
        return "fabric-order-list";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("fabricOrder", new FabricOrder());
        return "fabric-order-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute FabricOrder entity) {
        service.save(entity);
        return "redirect:/fabric-orders";
    }
}