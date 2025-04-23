package veniamin.backend.atelie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import veniamin.backend.atelie.entity.Complication;

@Repository
public interface ComplicationRepository extends JpaRepository<Complication, Integer> {
}