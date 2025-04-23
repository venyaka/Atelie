package veniamin.backend.atelie.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FabricType {
    @Id
    private Integer id;

    private String name;
}