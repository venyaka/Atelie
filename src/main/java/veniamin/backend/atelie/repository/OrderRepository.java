package veniamin.backend.atelie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import veniamin.backend.atelie.entity.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findByCutter_Id(Integer cutterId);
    List<Order> findByClientNameContainingIgnoreCase(String clientName);
}