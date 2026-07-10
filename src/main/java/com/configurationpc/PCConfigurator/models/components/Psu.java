package com.configurationpc.PCConfigurator.models.components;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("Psu")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Psu extends Components {

    @Column(nullable = false)
    private Integer totalPower;

    @Column(nullable = false)
    private String availableConnectors;
}
