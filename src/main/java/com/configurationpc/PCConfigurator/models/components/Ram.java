package com.configurationpc.PCConfigurator.models.components;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("Ram")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ram extends Components {

    @Column(nullable = false)
    private int memory;

    @Column(nullable = false)
    private String typeRam;

}
