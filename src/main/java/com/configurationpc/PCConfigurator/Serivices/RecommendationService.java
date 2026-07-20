package com.configurationpc.PCConfigurator.Serivices;

import com.configurationpc.PCConfigurator.models.components.*;
import com.configurationpc.PCConfigurator.validators.CompabilityValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RecommendationService {
    public List<String> recommend (List<Components> components) {
        List<String> recommendations = new ArrayList<>();


        Cpu cpu = CompabilityValidator.find(components, Cpu.class);
        Gpu gpu = CompabilityValidator.find(components, Gpu.class);
        Psu psu = CompabilityValidator.find(components, Psu.class);
        Motherboard motherboard = CompabilityValidator.find(components, Motherboard.class);
        Ram ram = CompabilityValidator.find(components, Ram.class);
        Cooler cooler = CompabilityValidator.find(components, Cooler.class);
        Case cases = CompabilityValidator.find(components, Case.class);

        if(cpu == null) {
            recommendations.add("Recommendation: add Cpu for your build");
        }

        if(gpu == null) {
            recommendations.add("Recommendation: add Gpu for your build");
        }

        if(psu == null) {
            recommendations.add("Recommendation: add Psu for your build");
        }

        if(motherboard == null) {
            recommendations.add("Recommendation: add Motherboard for your build");
        }

        if(ram == null) {
            recommendations.add("Recommendation: add Ram for your build");
        }

        if(cooler == null) {
            recommendations.add("Recommendation: add Cooler for your build");
        }

        if(cases == null) {
            recommendations.add("Recommendation: add Cases for your build");
        }

        return recommendations;
    }
}
