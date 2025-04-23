package veniamin.backend.atelie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import veniamin.backend.atelie.entity.FabricType;

@Repository
public interface FabricTypeRepository extends JpaRepository<FabricType, Integer> {
}