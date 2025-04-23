package veniamin.backend.atelie.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "order_")
@Getter
@Setter
@NoArgsConstructor
public class Order {
    @Id
    private Integer id;

    private Date dateOrder;

    @ManyToOne
    @JoinColumn(name = "cutter_id")
    private Cutter cutter;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private String clientName;
    private String clientAddress;
    private String clientPhone;
    private Date deliveryDate;
    private BigDecimal bonus;
}