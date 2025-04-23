package veniamin.backend.atelie.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import veniamin.backend.atelie.entity.CostOrder;
import veniamin.backend.atelie.repository.CostOrderRepository;
import veniamin.backend.atelie.service.CostOrderService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CostOrderServiceImpl implements CostOrderService {
    private final CostOrderRepository repository;

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','TAILOR','CLIENT')")
    public List<CostOrder> getAll() { return repository.findAll(); }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','TAILOR')")
    public CostOrder save(CostOrder entity) { return repository.save(entity); }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','TAILOR','CLIENT')")
    public Optional<CostOrder> getById(Integer id) { return repository.findById(id); }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(Integer id) { repository.deleteById(id); }
}
