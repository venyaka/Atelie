package veniamin.backend.atelie.service;

import org.springframework.web.multipart.MultipartFile;

public interface BackupService {
    String createBackup();
    String restoreBackup(MultipartFile file);
}
