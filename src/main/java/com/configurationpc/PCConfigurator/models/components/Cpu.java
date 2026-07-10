package com.configurationpc.PCConfigurator.models.components;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("Cpu")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cpu extends Components {

    @Column(nullable = false)
    private String socket;

    @Column(nullable = false)
    private Integer tdp;

}