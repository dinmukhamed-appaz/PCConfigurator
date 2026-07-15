package com.configurationpc.PCConfigurator.validators;

import com.configurationpc.PCConfigurator.models.components.Components;
import com.configurationpc.PCConfigurator.models.components.Gpu;
import com.configurationpc.PCConfigurator.models.components.Psu;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConnectorsValidate implements CompabilityValidator{

    @Override
    public void validate(List<Components> components, List<String> issues){
        Gpu gpu = CompabilityValidator.find(components, Gpu.class);
        Psu psu = CompabilityValidator.find(components, Psu.class);

        if(gpu != null || psu != null){
            String gpuConnectors = gpu.getPowerConnectors();
            String psuConnectors = psu.getAvailableConnectors();


            if(!gpuConnectors.contains(psuConnectors)){
                issues.add("Gpu connector not connect to psu");
            }
        }
    }
}
