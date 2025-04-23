package veniamin.backend.atelie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import veniamin.backend.atelie.entity.CutterCategory;

@Repository
public interface CutterCategoryRepository extends JpaRepository<CutterCategory, Integer> {
}