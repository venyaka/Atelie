package veniamin.backend.atelie.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import veniamin.backend.atelie.service.impl.BackupServiceImpl;

@Controller
@RequestMapping("/admin/backup")
public class BackupController {

    @Autowired
    private BackupServiceImpl backupService;

    @GetMapping
    public String showBackupPage(Model model) {
        return "backup";
    }

    @PostMapping("/create")
    public String createBackup(Model model) {
        String message = backupService.createBackup();
        model.addAttribute("message", message);
        return "backup";
    }

    @PostMapping("/restore")
    public String restoreBackup(@RequestParam("file") MultipartFile file, Model model) {
        String message = backupService.restoreBackup(file);
        model.addAttribute("message", message);
        return "backup";
    }
}
