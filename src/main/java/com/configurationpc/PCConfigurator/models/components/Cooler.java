package com.configurationpc.PCConfigurator.models.components;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("Cooler")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cooler extends Components {

    @Column(nullable = false)
    private String supportedSockets;

}
