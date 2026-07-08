package com.configurationpc.PCConfigurator.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Components")
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
    private String category;

    @Column(nullable = false)
    private Integer socket;

    @Column(nullable = false)
    private Integer energy;

    @Column(nullable = false)
    private Integer price;


}
