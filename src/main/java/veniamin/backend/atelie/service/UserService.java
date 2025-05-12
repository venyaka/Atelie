package veniamin.backend.atelie.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import veniamin.backend.atelie.entity.Role;
import veniamin.backend.atelie.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers();

    List<String> getAllTables();

    void updateUserRolesAndPermissions(Long userId, Set<Role> roles, Set<String> deniedTables);

    boolean isTableDeniedForCurrentUser(String tableName);

    Set<String> getDeniedTablesByEmail(String email);
}
