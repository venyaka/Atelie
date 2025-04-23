package veniamin.backend.atelie.entity;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cash {
    @Id
    private Integer invoiceId;

    private Date dateOrder;
    private BigDecimal amount;
}