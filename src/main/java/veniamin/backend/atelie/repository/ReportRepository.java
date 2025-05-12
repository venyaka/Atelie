package veniamin.backend.atelie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import veniamin.backend.atelie.dto.CompletedOrderDto;
import veniamin.backend.atelie.dto.CutterMonthOrdersDto;
import veniamin.backend.atelie.dto.FinancialReportDto;
import veniamin.backend.atelie.entity.User;


import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<User, Long> {

    @Query(value = """
    SELECT o.id as id,
           o.date_order as dateOrder,
           o.client_name as clientName,
           o.client_address as clientAddress,
           o.client_phone as clientPhone,
           o.bonus as bonus,
           p.name as productName
    FROM order_ o
    JOIN product p ON o.product_id = p.id
    WHERE o.cutter_id = :cutterId
      AND o.date_order <= GETDATE()
    """, nativeQuery = true)
    List<CompletedOrderDto> getCompletedOrdersByCutter(@Param("cutterId") Long cutterId);

    @Query(value = """
    SELECT o.id as id,
           o.date_order as dateOrder,
           c.name as cutterName,
           o.client_name as clientName,
           SUM(oc.base_amount + oc.complication_amount + oc.fabric_amount + oc.bonus_amount) as totalOrderAmount
    FROM order_ o
    JOIN cost_order oc ON o.id = oc.invoice_id
    JOIN cutter c ON o.cutter_id = c.id
    WHERE DAY(o.date_order) = :day
    GROUP BY o.id, o.date_order, c.name, o.client_name
    """, nativeQuery = true)
    List<FinancialReportDto> getFinancialReportByDay(@Param("day") int day);


    @Query(value = """
    SELECT DISTINCT o.cutter_id as cutterId,
                    c.name as cutterName,
                    cat.name as cutterCategory
    FROM order_ o
    JOIN cutter c ON o.cutter_id = c.id
    JOIN cutter_category cat ON c.id = cat.id
    WHERE MONTH(o.date_order) = MONTH(GETDATE())
      AND YEAR(o.date_order) = YEAR(GETDATE())
    """, nativeQuery = true)
    List<CutterMonthOrdersDto> getCurrentMonthCutterOrders();

}

