package veniamin.backend.atelie.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import veniamin.backend.atelie.entity.Product;
import veniamin.backend.atelie.repository.ProductRepository;
import veniamin.backend.atelie.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','TAILOR','CLIENT')")
    public List<Product> getAll() { return repository.findAll(); }

    @PreAuthorize("hasRole('ADMIN')")
    public Product save(Product entity) { return repository.save(entity); }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','TAILOR','CLIENT')")
    public Optional<Product> getById(Integer id) { return repository.findById(id); }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(Integer id) { repository.deleteById(id); }
}