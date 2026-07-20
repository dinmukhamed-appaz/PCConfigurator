package com.configurationpc.PCConfigurator.validators;

import com.configurationpc.PCConfigurator.models.components.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FormFactorValidate implements CompabilityValidator{

    @Override
    public void validate(List<Components> components, List<String> issues){
        Case casePc = CompabilityValidator.find(components, Case.class);
        Motherboard motherboard = CompabilityValidator.find(components, Motherboard.class);
        Gpu gpu = CompabilityValidator.find(components, Gpu.class);
        Cooler cooler = CompabilityValidator.find(components, Cooler.class);

        if (casePc != null && motherboard != null) {
            if (!CompabilityValidator.matchesAny(casePc.getSupportedFormFactors(), motherboard.getFormFactor())) {
                issues.add("Motherboard form can not fit to case form");
            }
        }

        if (casePc != null && cooler != null) {
            if (cooler.getHeightCooler() > casePc.getMaxCoolerHeight()) {
                issues.add("Cooler height too big for Case");
            }
        }

        if (casePc != null && gpu != null) {
            if (gpu.getLengthGpu() > casePc.getMaxGpuLength()) {
                issues.add("Gpu length too big for Case");
            }
        }
    }
}
