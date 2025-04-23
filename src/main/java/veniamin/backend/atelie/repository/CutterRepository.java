package veniamin.backend.atelie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import veniamin.backend.atelie.entity.Cutter;

import java.util.List;

@Repository
public interface CutterRepository extends JpaRepository<Cutter, Integer> {
    List<Cutter> findByCategory_Id(Integer categoryId);
}