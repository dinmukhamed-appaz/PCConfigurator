package com.configurationpc.PCConfigurator.validators;

import com.configurationpc.PCConfigurator.models.components.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FormFactorValidateTest {
    private final FormFactorValidate connectorsValidate = new FormFactorValidate();

    @Test
    void FormFactorMatchValidateTest() {
        Case casePc = new Case();
        casePc.setSupportedFormFactors("ATX, Micro-ATX");
        casePc.setMaxGpuLength(350);
        casePc.setMaxCoolerHeight(165);

        Motherboard motherboard = new Motherboard();
        motherboard.setFormFactor("ATX");

        Gpu gpu = new Gpu();
        gpu.setLengthGpu(300);

        Cooler cooler = new Cooler();
        cooler.setHeightCooler(155);

        List<Components> caseMother = List.of(casePc, motherboard);
        List<Components> caseCooler = List.of(casePc, cooler);
        List<Components> caseGpu = List.of(casePc, gpu);
        List<String> issues = new ArrayList<>();

        connectorsValidate.validate(caseMother, issues);
        connectorsValidate.validate(caseCooler, issues);
        connectorsValidate.validate(caseGpu, issues);

        assertTrue(issues.isEmpty());

    }

    @Test
    void FormFactorNotMatchValidateTest() {
        Case casePc = new Case();
        casePc.setSupportedFormFactors("Micro-ATX");
        casePc.setMaxGpuLength(350);
        casePc.setMaxCoolerHeight(165);

        Motherboard motherboard = new Motherboard();
        motherboard.setFormFactor("ATX");

        Gpu gpu = new Gpu();
        gpu.setLengthGpu(360);

        Cooler cooler = new Cooler();
        cooler.setHeightCooler(170);

        List<Components> caseMother = List.of(casePc, motherboard);
        List<Components> caseCooler = List.of(casePc, cooler);
        List<Components> caseGpu = List.of(casePc, gpu);
        List<String> issues = new ArrayList<>();

        connectorsValidate.validate(caseMother, issues);
        connectorsValidate.validate(caseCooler, issues);
        connectorsValidate.validate(caseGpu, issues);

        assertEquals(3, issues.size());
        assertEquals("Motherboard form can not fit to case form", issues.get(0));
        assertEquals("Cooler height too big for Case", issues.get(1));
        assertEquals("Gpu length too big for Case", issues.get(2));

    }
}
