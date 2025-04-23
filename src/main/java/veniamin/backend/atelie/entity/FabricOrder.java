package veniamin.backend.atelie.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FabricOrder {
    @EmbeddedId
    private FabricOrderId id;

    @ManyToOne
    @MapsId("invoiceId")
    @JoinColumn(name = "invoice_id")
    private Order order;

    @ManyToOne
    @MapsId("article")
    @JoinColumn(name = "article")
    private Fabric fabric;

    private Integer countOfFabric;
}