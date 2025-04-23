package veniamin.backend.atelie.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import veniamin.backend.atelie.entity.Order;
import veniamin.backend.atelie.repository.OrderRepository;
import veniamin.backend.atelie.service.OrderService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','TAILOR','CLIENT')")
    public List<Order> getAll() { return repository.findAll(); }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','TAILOR')")
    public Order save(Order order) { return repository.save(order); }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','TAILOR','CLIENT')")
    public Optional<Order> getById(Integer id) { return repository.findById(id); }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(Integer id) { repository.deleteById(id); }
}