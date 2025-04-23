package veniamin.backend.atelie.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import veniamin.backend.atelie.entity.ComplicationOrder;
import veniamin.backend.atelie.entity.ComplicationOrderId;
import veniamin.backend.atelie.repository.ComplicationOrderRepository;
import veniamin.backend.atelie.service.ComplicationOrderService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComplicationOrderServiceImpl implements ComplicationOrderService {
    private final ComplicationOrderRepository repository;

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','CUTTER','CLIENT')")
    public List<ComplicationOrder> getAll() { return repository.findAll(); }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','CUTTER')")
    public ComplicationOrder save(ComplicationOrder entity) { return repository.save(entity); }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','CUTTER','CLIENT')")
    public Optional<ComplicationOrder> getById(ComplicationOrderId id) { return repository.findById(id); }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(ComplicationOrderId id) { repository.deleteById(id); }
}