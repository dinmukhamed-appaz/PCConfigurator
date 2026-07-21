package com.configurationpc.PCConfigurator.validators;


import com.configurationpc.PCConfigurator.models.components.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MinimumRequiredValidators implements CompabilityValidator {

    @Override
    public void validate(List<Components> components, List<String> issues) {
        Cpu cpu = CompabilityValidator.find(components, Cpu.class);
        Psu psu = CompabilityValidator.find(components, Psu.class);
        Motherboard motherboard = CompabilityValidator.find(components, Motherboard.class);
        Ram ram = CompabilityValidator.find(components, Ram.class);

        if (cpu == null){
            issues.add("You need to add" + cpu);
        }
        if (psu == null){
            issues.add("You need to add" + psu);
        }
        if (motherboard == null){
            issues.add("You need to add" + motherboard);
        }
        if (ram == null){
            issues.add("You need to add" + ram);
        }


    }
}
