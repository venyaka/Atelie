package veniamin.backend.atelie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import veniamin.backend.atelie.entity.FabricOrder;
import veniamin.backend.atelie.entity.FabricOrderId;

import java.util.List;

@Repository
public interface FabricOrderRepository extends JpaRepository<FabricOrder, FabricOrderId> {
    List<FabricOrder> findById_InvoiceId(Integer invoiceId);
}