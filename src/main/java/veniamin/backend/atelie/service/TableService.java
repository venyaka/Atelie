package veniamin.backend.atelie.service;

import java.util.List;
import java.util.Map;

public interface TableService {

    List<Map<String, Object>> getAll(String table);

    List<String> getFields(String table);

    void addRecord(String table, Map<String, String> data);

    void editRecord(String table, String id, Map<String, String> data);

    void deleteRecord(String table, String id);
}
