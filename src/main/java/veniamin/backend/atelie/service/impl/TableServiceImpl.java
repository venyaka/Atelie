package veniamin.backend.atelie.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import veniamin.backend.atelie.constant.RoleAccessConstants;
import veniamin.backend.atelie.entity.Role;
import veniamin.backend.atelie.entity.User;
import veniamin.backend.atelie.exception.BadRequestException;
import veniamin.backend.atelie.exception.NotFoundException;
import veniamin.backend.atelie.exception.errors.BadRequestError;
import veniamin.backend.atelie.exception.errors.NotFoundError;
import veniamin.backend.atelie.repository.UserRepository;
import veniamin.backend.atelie.service.TableService;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TableServiceImpl implements TableService {

    private final JdbcTemplate jdbcTemplate;

    private final UserRepository userRepository;

    @Override
    public List<Map<String, Object>> getAll(String table) {
        String selectOperation = "SELECT";
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Optional<User> optionalUser = userRepository.findByEmail(auth.getName());
        if (optionalUser.isEmpty()) {
            throw new NotFoundException(NotFoundError.USER_NOT_FOUND);
        }

        User user = optionalUser.get();
        List<Role> roles = (List<Role>) auth.getAuthorities();

        for (Role role : roles) {
            if (RoleAccessConstants.PERMISSIONS.get(role).get(selectOperation).contains(table)) {
                return jdbcTemplate.queryForList("SELECT * FROM " + table);
            }
            if (RoleAccessConstants.ONLY_USER_PERMISSIONS.get(role).get(selectOperation).containsKey(table)) {
                String idName = RoleAccessConstants.ONLY_USER_PERMISSIONS.get(role).get(selectOperation).get(table);
                if (table.equals("order_")) return jdbcTemplate.queryForList("SELECT * FROM " + table + " WHERE " + idName + " = ?", idName.equals("client_name") ? user.getName() : user.getId());
                return jdbcTemplate.queryForList("SELECT t.* FROM " + table + " t JOIN order_ o ON t.invoice_id = o.id WHERE o. " + idName + " = ?", idName.equals("client_name") ? user.getName() : user.getId());
            }
        }

//        if (roles.contains(Role.ROLE_CUTTER) && (table.equals("order_") || table.equals("complication_order") || table.equals("fabric_order") || table.equals("cost_order"))) {
//            Integer cutterId = jdbcTemplate.queryForObject(
//                    "SELECT id FROM users WHERE email = ?", Integer.class, auth.getName());
//
//            return jdbcTemplate.queryForList("SELECT * FROM order_ WHERE cutter_id = ?", cutterId);
//        }

//        return jdbcTemplate.queryForList("SELECT * FROM " + table);
        return data;
    }

    @Override
    public List<String> getFields(String table) {
        return jdbcTemplate.queryForList(
                        "SELECT column_name FROM information_schema.columns WHERE table_name = ?",
                        String.class,
                        table
                ).stream()
                .map(String::toLowerCase)
                .toList();
    }


    @Override
    public void addRecord(String table, Map<String, String> data) {
        String columns = String.join(",", data.keySet());
        String values = String.join(",", Collections.nCopies(data.size(), "?"));
        jdbcTemplate.update("INSERT INTO " + table + " (" + columns + ") VALUES (" + values + ")", data.values().toArray());
    }

    @Override
    public void editRecord(String table, String id, Map<String, String> data) {
        String setClause = String.join(",", data.keySet().stream().map(k -> k + "=?").toList());
        List<Object> params = new ArrayList<>(data.values());
        params.add(id);
        jdbcTemplate.update("UPDATE " + table + " SET " + setClause + " WHERE id = ?", params.toArray());
    }

    @Override
    public void deleteRecord(String table, String id) {
        jdbcTemplate.update("DELETE FROM " + table + " WHERE id = ?", id);
    }
}
