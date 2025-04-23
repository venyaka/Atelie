package veniamin.backend.atelie.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import veniamin.backend.atelie.entity.Fabric;
import veniamin.backend.atelie.repository.FabricRepository;
import veniamin.backend.atelie.service.FabricService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FabricServiceImpl implements FabricService {
    private final FabricRepository repository;

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','TAILOR','CLIENT')")
    public List<Fabric> getAll() { return repository.findAll(); }

    @PreAuthorize("hasRole('ADMIN')")
    public Fabric save(Fabric entity) { return repository.save(entity); }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','TAILOR','CLIENT')")
    public Optional<Fabric> getById(Integer id) { return repository.findById(id); }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(Integer id) { repository.deleteById(id); }
}