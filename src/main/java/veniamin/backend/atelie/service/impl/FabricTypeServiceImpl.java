package veniamin.backend.atelie.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import veniamin.backend.atelie.entity.FabricType;
import veniamin.backend.atelie.repository.FabricTypeRepository;
import veniamin.backend.atelie.service.FabricTypeService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FabricTypeServiceImpl implements FabricTypeService {
    private final FabricTypeRepository repository;

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','TAILOR','CLIENT')")
    public List<FabricType> getAll() { return repository.findAll(); }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','TAILOR','CLIENT')")
    public Optional<FabricType> getById(Integer id) { return repository.findById(id); }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(Integer id) { repository.deleteById(id); }
}