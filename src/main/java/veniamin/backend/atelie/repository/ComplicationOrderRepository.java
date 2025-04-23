package veniamin.backend.atelie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import veniamin.backend.atelie.entity.ComplicationOrder;
import veniamin.backend.atelie.entity.ComplicationOrderId;

import java.util.List;

@Repository
public interface ComplicationOrderRepository extends JpaRepository<ComplicationOrder, ComplicationOrderId> {
    List<ComplicationOrder> findById_InvoiceId(Integer invoiceId);
}