package com.configurationpc.PCConfigurator.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "builds")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Build {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double totalPrice = 0.0;

    @OneToMany(mappedBy = "build", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BuildComponent> components = new ArrayList<>();
}