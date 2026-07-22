package com.configurationpc.PCConfigurator.models;


import com.configurationpc.PCConfigurator.models.components.Components;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "build_pc")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Build {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private double totalPrice = 0;

    boolean status = false;

    @ManyToMany
    @JoinTable(
            name = "build_components",
            joinColumns = @JoinColumn(name = "build_id"),
            inverseJoinColumns = @JoinColumn(name = "component_id")
    )
    private List<Components> components = new ArrayList<>();



}
