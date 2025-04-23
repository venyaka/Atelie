package veniamin.backend.atelie.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import veniamin.backend.atelie.entity.FabricOrder;
import veniamin.backend.atelie.entity.FabricOrderId;
import veniamin.backend.atelie.repository.FabricOrderRepository;
import veniamin.backend.atelie.service.FabricOrderService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FabricOrderServiceImpl implements FabricOrderService {
    private final FabricOrderRepository repository;

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','CUTTER','CLIENT')")
    public List<FabricOrder> getAll() { return repository.findAll(); }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','CUTTER')")
    public FabricOrder save(FabricOrder entity) { return repository.save(entity); }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','CUTTER','CLIENT')")
    public Optional<FabricOrder> getById(FabricOrderId id) { return repository.findById(id); }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(FabricOrderId id) { repository.deleteById(id); }
}
