package veniamin.backend.atelie.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cutter {
    @Id
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "cutter_category")
    private CutterCategory category;
}
