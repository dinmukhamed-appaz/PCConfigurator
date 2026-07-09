package com.configurationpc.PCConfigurator.models.components;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pcCase")
@DiscriminatorValue("Case")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Case extends Components {

    @Column(nullable = false)
    private String supportedFormFactors;
}
