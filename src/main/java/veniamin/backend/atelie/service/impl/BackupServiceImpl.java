package veniamin.backend.atelie.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import veniamin.backend.atelie.service.BackupService;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class BackupServiceImpl implements BackupService {

    private final String backupDir = "C:\\Temp\\"; // путь к папке для бэкапов

    private String dbUrl = "jdbc:sqlserver://VENIAMIN;encrypt=true;trustServerCertificate=true;database=Atelie"; // имя вашей базы

    @Value("${spring.datasource.username}")
    private String username;


    @Value("${spring.datasource.username}")
    private String password;


    private String extractDatabaseName(String url) {
        String prefix = "databaseName=";
        int start = url.indexOf(prefix);
        if (start == -1) return null;
        int end = url.indexOf(";", start);
        return end == -1 ? url.substring(start + prefix.length()) :
                url.substring(start + prefix.length(), end);
    }


    public String createBackup() {
        String dbName = extractDatabaseName(dbUrl);
        String filename = "backup_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".bak";
        String backupPath = backupDir + filename;

        String sql = "BACKUP DATABASE [" + dbName + "] TO DISK = N'" + backupPath + "'";

        try (Connection conn = DriverManager.getConnection(dbUrl, username, password);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            return "Резервная копия успешно создана: " + filename;
        } catch (Exception e) {
            e.printStackTrace();
            return "Ошибка при создании резервной копии: " + e.getMessage();
        }
    }

    public String restoreBackup(MultipartFile file) {
        if (file.isEmpty()) {
            return "Файл не выбран.";
        }
        String dbName = extractDatabaseName(dbUrl);

        try {
            // Сохраняем файл временно
            Path tempFile = Files.createTempFile("restore_", ".bak");
            file.transferTo(tempFile);

            String sqlSetSingleUser = "ALTER DATABASE [" + dbName + "] SET SINGLE_USER WITH ROLLBACK IMMEDIATE";
            String sqlRestore = "RESTORE DATABASE [" + dbName + "] FROM DISK = N'" + tempFile.toAbsolutePath() + "' WITH REPLACE";
            String sqlSetMultiUser = "ALTER DATABASE [" + dbName + "] SET MULTI_USER";

            try (Connection conn = DriverManager.getConnection(dbUrl, username, password);
                 Statement stmt = conn.createStatement()) {

                stmt.execute(sqlSetSingleUser);
                stmt.execute(sqlRestore);
                stmt.execute(sqlSetMultiUser);

                return "База данных успешно восстановлена из файла: " + file.getOriginalFilename();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Ошибка при восстановлении: " + e.getMessage();
        }
    }
}
