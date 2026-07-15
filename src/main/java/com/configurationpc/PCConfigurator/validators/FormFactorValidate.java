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

        if(casePc != null && motherboard != null && gpu != null && cooler != null){
            String casePcForm = casePc.getSupportedFormFactors();
            String motherboardForm = motherboard.getFormFactor();
            int caseMaxGpu = casePc.getMaxGpuLength();
            int caseMaxCooler = casePc.getMaxCoolerHeight();
            int gpuLength = gpu.getLengthGpu();
            int coolerHeight = cooler.getHeightCooler();

            if(!casePcForm.contains(motherboardForm)){
                issues.add("Motherboard form can not fit to case form");
            }else if(!(coolerHeight <= caseMaxCooler)){
                issues.add("Cooler height too big for Case");
            }else if(!(caseMaxGpu >= gpuLength)){
                issues.add("Gpu length too big for Case");
            }
        }
    }
}
