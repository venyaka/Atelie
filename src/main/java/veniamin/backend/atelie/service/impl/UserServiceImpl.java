package veniamin.backend.atelie.service.impl;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import veniamin.backend.atelie.entity.Role;
import veniamin.backend.atelie.entity.User;
import veniamin.backend.atelie.exception.NotFoundException;
import veniamin.backend.atelie.exception.errors.NotFoundError;
import veniamin.backend.atelie.filter.jwt.JwtUtils;
import veniamin.backend.atelie.repository.UserRepository;
import veniamin.backend.atelie.service.UserService;
import veniamin.backend.atelie.utils.LogsUtils;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final MailServiceImpl mailService;

    private final LogsUtils logsUtils;

    private final JwtUtils jwtUtils;

    private final JdbcTemplate jdbcTemplate;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername " + "email " + email);
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new NotFoundException(NotFoundError.USER_NOT_FOUND));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<String> getAllTables() {
        String sql = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE = 'BASE TABLE' AND TABLE_SCHEMA = 'dbo'";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    @Override
    @Transactional
    public void updateUserRolesAndPermissions(Long userId, Set<Role> roles, Set<String> deniedTables) {
        // Удаляем текущие роли пользователя
        jdbcTemplate.update("DELETE FROM user_role WHERE user_id = ?", userId);

        // Добавляем новые роли
        for (Role role : roles) {
            jdbcTemplate.update("INSERT INTO user_role (user_id, user_role) VALUES (?, ?)", userId, role.name());
        }

        // Удаляем текущие запреты
        jdbcTemplate.update("DELETE FROM user_denied_tables WHERE user_id = ?", userId);

        // Добавляем новые запреты
        for (String table : deniedTables) {
            jdbcTemplate.update("INSERT INTO user_denied_tables (user_id, table_name) VALUES (?, ?)", userId, table);
        }
    }

    @Override
    public boolean isTableDeniedForCurrentUser(String tableName) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        return userRepository.isTableDenied(username, tableName);
    }

    @Override
    public Set<String> getDeniedTablesByEmail(String email) {
        return userRepository.findDeniedTablesByEmail(email);
    }


}
