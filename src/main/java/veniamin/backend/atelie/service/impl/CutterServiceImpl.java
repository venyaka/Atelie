package veniamin.backend.atelie.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import veniamin.backend.atelie.entity.Cutter;
import veniamin.backend.atelie.repository.CutterRepository;
import veniamin.backend.atelie.service.CutterService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CutterServiceImpl implements CutterService {
    private final CutterRepository repository;

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','TAILOR')")
    public List<Cutter> getAll() { return repository.findAll(); }

    @PreAuthorize("hasRole('ADMIN')")
    public Cutter save(Cutter entity) { return repository.save(entity); }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','TAILOR')")
    public Optional<Cutter> getById(Integer id) { return repository.findById(id); }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(Integer id) { repository.deleteById(id); }
}