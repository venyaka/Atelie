package veniamin.backend.atelie.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import veniamin.backend.atelie.service.FabricTypeService;
import veniamin.backend.atelie.service.impl.FabricTypeServiceImpl;

import java.util.Optional;

@Controller
@RequestMapping("/fabric-types")
@RequiredArgsConstructor
public class FabricTypeController {
    private final FabricTypeServiceImpl service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("fabricTypes", service.getAll());
        return "fabric-type-list";
    }
}