package veniamin.backend.atelie.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import veniamin.backend.atelie.entity.Complication;
import veniamin.backend.atelie.repository.ComplicationRepository;
import veniamin.backend.atelie.service.ComplicationService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComplicationServiceImpl implements ComplicationService {
    private final ComplicationRepository repository;

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','TAILOR','CLIENT')")
    public List<Complication> getAll() { return repository.findAll(); }

    @PreAuthorize("hasRole('ADMIN')")
    public Complication save(Complication entity) { return repository.save(entity); }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','TAILOR','CLIENT')")
    public Optional<Complication> getById(Integer id) { return repository.findById(id); }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(Integer id) { repository.deleteById(id); }
}