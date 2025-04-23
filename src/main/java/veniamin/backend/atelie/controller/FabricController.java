package veniamin.backend.atelie.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import veniamin.backend.atelie.entity.Fabric;
import veniamin.backend.atelie.service.impl.FabricServiceImpl;

import java.util.Optional;

@Controller
@RequestMapping("/fabrics")
@RequiredArgsConstructor
public class FabricController {
    private final FabricServiceImpl service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("fabrics", service.getAll());
        return "fabric-list";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("fabric", new Fabric());
        return "fabric-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Fabric entity) {
        service.save(entity);
        return "redirect:/fabrics";
    }
}