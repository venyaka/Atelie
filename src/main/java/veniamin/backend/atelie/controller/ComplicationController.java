package veniamin.backend.atelie.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import veniamin.backend.atelie.entity.Complication;
import veniamin.backend.atelie.service.impl.ComplicationServiceImpl;

@Controller
@RequestMapping("/complications")
@RequiredArgsConstructor
public class ComplicationController {
    private final ComplicationServiceImpl service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("complications", service.getAll());
        return "complication-list";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("complication", new Complication());
        return "complication-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Complication entity) {
        service.save(entity);
        return "redirect:/complications";
    }
}