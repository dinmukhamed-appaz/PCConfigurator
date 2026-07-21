package com.configurationpc.PCConfigurator.Services;

import com.configurationpc.PCConfigurator.exceptions.ComponentsNotFoundException;
import com.configurationpc.PCConfigurator.exceptions.RecommendationException;
import com.configurationpc.PCConfigurator.models.components.*;
import com.configurationpc.PCConfigurator.validators.CompabilityValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CheckerService {

    private final List<CompabilityValidator> compabilityValidators;

    private final RecommendationService recommendationService;



    public List<String> check(List<Components> components) {
        List<String> issues = new ArrayList<>();

        if(components == null) {
            throw new ComponentsNotFoundException("Components not found");
        }

        for (CompabilityValidator validators : compabilityValidators) {
            validators.validate(components, issues);
        }

        return issues;

    }

    public double totalPrice(List<Components> components) {

        double totalPrice = 0;
        for(Components component : components) {
            totalPrice += component.getPrice();
        }
        return totalPrice;
    }


}
