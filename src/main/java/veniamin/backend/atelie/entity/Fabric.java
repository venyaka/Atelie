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
public class Fabric {
    @Id
    private Integer article;

    private String fabricName;

    private String unit;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "fabric_type_id")
    private FabricType type;

    private Integer countOfFabric;
}