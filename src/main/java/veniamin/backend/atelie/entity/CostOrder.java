package veniamin.backend.atelie.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CostOrder {
    @Id
    private Integer invoiceId;

    private BigDecimal baseAmount;
    private BigDecimal complicationAmount;
    private BigDecimal fabricAmount;
    private BigDecimal bonusAmount;
}