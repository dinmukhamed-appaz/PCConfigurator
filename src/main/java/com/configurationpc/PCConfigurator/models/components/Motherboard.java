package com.configurationpc.PCConfigurator.models.components;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@DiscriminatorValue("Motherboard")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Motherboard extends Components {
    @Column(nullable = false)
    private String socket;

    @Column(nullable = false)
    private String ramType;

    @Column(nullable = false)
    private String formFactor ;
}

