package veniamin.backend.atelie.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ComplicationOrder {

    @EmbeddedId
    private ComplicationOrderId id;

    @ManyToOne
    @MapsId("invoiceId")
    @JoinColumn(name = "invoice_id")
    private Order order;

    @ManyToOne
    @MapsId("complicationId")
    @JoinColumn(name = "complication_id")
    private Complication complication;

    private Integer countOfElement;
}