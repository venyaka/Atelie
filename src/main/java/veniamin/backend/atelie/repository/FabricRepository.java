package veniamin.backend.atelie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import veniamin.backend.atelie.entity.Fabric;

import java.util.List;

@Repository
public interface FabricRepository extends JpaRepository<Fabric, Integer> {
    List<Fabric> findByArticle(Integer typeId);
}