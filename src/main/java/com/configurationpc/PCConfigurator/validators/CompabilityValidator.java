package com.configurationpc.PCConfigurator.validators;

import com.configurationpc.PCConfigurator.models.components.Components;

import java.util.List;

public interface CompabilityValidator {

    void validate(List<Components> components, List<String> issues);

    static  <T extends Components> T find(List<Components> components, Class<T> type) {
        for (Components component : components) {
            if(type.isInstance(component)){
                return (T) component;
            }
        }
        return null;
    }
}