package veniamin.backend.atelie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import veniamin.backend.atelie.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Optional<User> findByEmail(String email);

    @Query("SELECT CASE WHEN COUNT(udt) > 0 THEN true ELSE false END " +
            "FROM UserDeniedTable udt " +
            "WHERE udt.user.email = :email AND udt.tableName = :tableName")
    boolean isTableDenied(@Param("email") String email, @Param("tableName") String tableName);

    @Query("SELECT udt.tableName FROM UserDeniedTable udt WHERE udt.user.email = :email")
    Set<String> findDeniedTablesByEmail(@Param("email") String email);

}
