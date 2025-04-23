package veniamin.backend.atelie.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import veniamin.backend.atelie.entity.CutterCategory;
import veniamin.backend.atelie.service.impl.CutterCategoryServiceImpl;

import java.util.Optional;

@Controller
@RequestMapping("/cutter-categories")
@RequiredArgsConstructor
public class CutterCategoryController {
    private final CutterCategoryServiceImpl service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("cutterCategories", service.getAll());
        return "cutter-category-list";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("cutterCategory", new CutterCategory());
        return "cutter-category-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute CutterCategory entity) {
        service.save(entity);
        return "redirect:/cutter-categories";
    }
}