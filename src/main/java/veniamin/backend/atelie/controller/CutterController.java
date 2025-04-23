package veniamin.backend.atelie.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import veniamin.backend.atelie.entity.Cutter;
import veniamin.backend.atelie.service.impl.CutterServiceImpl;

import java.util.Optional;

@Controller
@RequestMapping("/cutters")
@RequiredArgsConstructor
public class CutterController {
    private final CutterServiceImpl service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("cutters", service.getAll());
        return "cutter-list";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("cutter", new Cutter());
        return "cutter-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Cutter cutter) {
        service.save(cutter);
        return "redirect:/cutters";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.deleteById(id);
        return "redirect:/cutters";
    }
}