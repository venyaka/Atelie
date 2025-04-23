package veniamin.backend.atelie.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import veniamin.backend.atelie.entity.Cash;
import veniamin.backend.atelie.repository.CashRepository;
import veniamin.backend.atelie.service.CashService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CashServiceImpl implements CashService {
    private final CashRepository repository;

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public List<Cash> getAll() { return repository.findAll(); }

    @PreAuthorize("hasRole('ADMIN')")
    public Cash save(Cash entity) { return repository.save(entity); }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public Optional<Cash> getById(Integer id) { return repository.findById(id); }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(Integer id) { repository.deleteById(id); }
}