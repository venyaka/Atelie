package veniamin.backend.atelie.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import veniamin.backend.atelie.entity.CutterCategory;
import veniamin.backend.atelie.repository.CutterCategoryRepository;
import veniamin.backend.atelie.service.CutterCategoryService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CutterCategoryServiceImpl implements CutterCategoryService {
    private final CutterCategoryRepository repository;

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','CUTTER')")
    public List<CutterCategory> getAll() { return repository.findAll(); }

    @PreAuthorize("hasRole('ADMIN')")
    public CutterCategory save(CutterCategory entity) { return repository.save(entity); }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','CUTTER')")
    public Optional<CutterCategory> getById(Integer id) { return repository.findById(id); }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(Integer id) { repository.deleteById(id); }
}