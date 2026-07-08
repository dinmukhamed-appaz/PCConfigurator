package com.configurationpc.PCConfigurator.models.components;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "components")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Components {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;


}
