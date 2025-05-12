package veniamin.backend.atelie.service.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import veniamin.backend.atelie.service.QueryService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class QueryServiceImpl implements QueryService {

    private final JdbcTemplate jdbcTemplate;

    public QueryServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> findOrdersBySurname(String surname) {
        if (surname == null || surname.isBlank()) return List.of();
        String sql = "SELECT * FROM order_ WHERE client_name LIKE ?";
        return jdbcTemplate.queryForList(sql, new Object[]{"%" + surname + "%"}, String.class);
    }

    public List<String> countOrdersPerCutterCurrentYear() {
        String sql = "SELECT c.name, COUNT(*) as total FROM order_ o JOIN cutter c ON o.cutter_id = c.id " +
                "WHERE YEAR(o.date_order) = YEAR(GETDATE()) GROUP BY c.name";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("name") + ": " + rs.getInt("total"));
    }

    public Integer getPurchasedFabricCount(String article) {
        if (article == null || article.isBlank()) return 0;
        String sql = "SELECT SUM(quantity) FROM fabric_order WHERE fabric_id IN " +
                "(SELECT id FROM fabric WHERE article = ?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{article}, Integer.class);
    }

    public List<String> getOrdersDueByDate(LocalDate dueDate) {
        if (dueDate == null) return List.of();
        String sql = "SELECT * FROM order_ WHERE date_order <= ?";
        return jdbcTemplate.queryForList(sql, new Object[]{Date.valueOf(dueDate)}, String.class);
    }

    public List<String> getProductCategoriesByMonth(Integer month) {
        if (month == null) return List.of();
        String sql = "SELECT DISTINCT p.category FROM order_ o JOIN product p ON o.product_id = p.id " +
                "WHERE MONTH(o.date_order) = ? AND YEAR(o.date_order) = YEAR(GETDATE())";
        return jdbcTemplate.queryForList(sql, new Object[]{month}, String.class);
    }
}