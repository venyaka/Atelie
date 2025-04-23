package veniamin.backend.atelie.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class ComplicationOrderId implements java.io.Serializable {

    @Column
    private Integer invoiceId;

    @Column
    private Integer complicationId;
}