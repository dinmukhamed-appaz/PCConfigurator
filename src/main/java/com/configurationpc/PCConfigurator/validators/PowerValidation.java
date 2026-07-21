package com.configurationpc.PCConfigurator.validators;

import com.configurationpc.PCConfigurator.models.components.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PowerValidation implements CompabilityValidator{

    @Override
    public void validate(List<Components> components, List<String> issues) {
        Cooler cooler = CompabilityValidator.find(components, Cooler.class);
        Cpu cpu = CompabilityValidator.find(components, Cpu.class);
        Gpu gpu = CompabilityValidator.find(components, Gpu.class);
        Psu psu = CompabilityValidator.find(components, Psu.class);

        if(cooler != null && cpu != null && gpu != null && psu != null) {
            int coolerTdp = cooler.getMaxTdp();
            int cpuTdp = cpu.getTdp();
            int gpuTdp = gpu.getPowerConsumption();
            int psuTdp = psu.getTotalPower();

            if(psuTdp < coolerTdp + cpuTdp + gpuTdp) {
                issues.add("Psu power is not enough");
            }

        }
    }
}
