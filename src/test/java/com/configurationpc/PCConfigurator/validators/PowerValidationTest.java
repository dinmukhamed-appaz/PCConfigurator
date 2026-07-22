package com.configurationpc.PCConfigurator.validators;

import com.configurationpc.PCConfigurator.models.components.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PowerValidationTest{
    private final PowerValidation powerValidation = new PowerValidation();

    @Test
    void powerValidationEnoughTest(){
        Cooler cooler = new Cooler();
        cooler.setMaxTdp(200);

        Cpu cpu = new Cpu();
        cpu.setTdp(100);

        Gpu gpu = new Gpu();
        gpu.setPowerConsumption(120);

        Psu psu = new Psu();
        psu.setTotalPower(800);

        List<Components> components = List.of(cooler, cpu, gpu, psu);
        List<String> issues = new ArrayList<>();

        powerValidation.validate(components, issues);

        assertTrue(issues.isEmpty());
    }

    @Test
    void powerValidationNotEnoughTest(){
        Cooler cooler = new Cooler();
        cooler.setMaxTdp(200);

        Cpu cpu = new Cpu();
        cpu.setTdp(100);

        Gpu gpu = new Gpu();
        gpu.setPowerConsumption(120);

        Psu psu = new Psu();
        psu.setTotalPower(300);

        List<Components> components = List.of(cooler, cpu, gpu, psu);
        List<String> issues = new ArrayList<>();

        powerValidation.validate(components, issues);

        assertEquals(1, issues.size());
        assertEquals("Psu power is not enough", issues.get(0));

    }
}
