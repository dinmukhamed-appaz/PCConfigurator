package com.configurationpc.PCConfigurator.validators;

import com.configurationpc.PCConfigurator.models.components.Components;
import com.configurationpc.PCConfigurator.models.components.Motherboard;
import com.configurationpc.PCConfigurator.models.components.Ram;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RamTypeValidator implements CompabilityValidator {

    @Override
    public void validate(List<Components> components, List<String> issues) {
        Motherboard motherboard = CompabilityValidator.find(components, Motherboard.class);
        Ram ram = CompabilityValidator.find(components, Ram.class);

        if (ram != null && motherboard != null) {
            String ramTypeMother = motherboard.getRamType();
            String ramType = ram.getTypeRam();

            if (!ramType.equalsIgnoreCase(ramTypeMother)) {
                issues.add("Ram type do not match with motherboard");
            }

        }

    }
}

