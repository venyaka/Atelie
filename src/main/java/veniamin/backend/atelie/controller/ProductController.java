package veniamin.backend.atelie.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import veniamin.backend.atelie.entity.Product;
import veniamin.backend.atelie.service.ProductService;
import veniamin.backend.atelie.service.impl.ProductServiceImpl;

import java.util.Optional;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductServiceImpl service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", service.getAll());
        return "product-list";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Product entity) {
        service.save(entity);
        return "redirect:/products";
    }
}