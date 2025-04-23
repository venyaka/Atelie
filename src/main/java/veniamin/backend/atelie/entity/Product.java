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
public class Product {
    @Id
    private Integer id;

    private String name;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "cutter_category")
    private CutterCategory category;
}