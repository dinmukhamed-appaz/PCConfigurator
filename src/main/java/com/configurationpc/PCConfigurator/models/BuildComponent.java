package com.configurationpc.PCConfigurator.models;

import com.configurationpc.PCConfigurator.models.components.Components;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "build_components")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuildComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "build_id", nullable = false)
    private Build build;

    @ManyToOne
    @JoinColumn(name = "component_id", nullable = false)
    private Components component;

}