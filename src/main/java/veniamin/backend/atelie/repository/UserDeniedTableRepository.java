package veniamin.backend.atelie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import veniamin.backend.atelie.entity.User;
import veniamin.backend.atelie.entity.UserDeniedTable;

import java.util.List;

public interface UserDeniedTableRepository extends JpaRepository<UserDeniedTable, Long> {

    List<UserDeniedTable> findAllByUser(User user);

    void deleteAllByUser(User user);

    boolean existsByUserAndTableName(User user, String tableName);
}
