package veniamin.backend.atelie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import veniamin.backend.atelie.entity.Cash;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface CashRepository extends JpaRepository<Cash, Integer> {
    List<Cash> findByDateOrderBetween(Date start, Date end);
}