package veniamin.backend.atelie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import veniamin.backend.atelie.entity.CostOrder;

@Repository
public interface CostOrderRepository extends JpaRepository<CostOrder, Integer> {
}