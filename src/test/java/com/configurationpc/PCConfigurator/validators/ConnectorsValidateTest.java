package com.configurationpc.PCConfigurator.validators;


import com.configurationpc.PCConfigurator.models.components.Components;
import com.configurationpc.PCConfigurator.models.components.Gpu;
import com.configurationpc.PCConfigurator.models.components.Psu;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConnectorsValidateTest {

    private final ConnectorsValidate connectorsValidate = new ConnectorsValidate();

    @Test
    void ConnectorsValidateMatchTest() {
        Gpu gpu = new Gpu();
        gpu.setPowerConnectors("8-pin");

        Psu psu = new Psu();
        psu.setAvailableConnectors("6-pin, 8-pin, 12VHPWR");

        List<Components> components = List.of(gpu, psu);
        List<String> issues = new ArrayList<>();

        connectorsValidate.validate(components, issues);

        assertTrue(issues.isEmpty());
    }

    @Test
    void ConnectorsValidateNotMatchTest2() {
        Gpu gpu = new Gpu();
        gpu.setPowerConnectors("12VHPWR");

        Psu psu = new Psu();
        psu.setAvailableConnectors("6-pin, 8-pin");

        List<Components> components = List.of(gpu, psu);
        List<String> issues = new ArrayList<>();

        connectorsValidate.validate(components, issues);

        assertEquals(1, issues.size());
        assertEquals("Gpu connector not connect to psu", issues.get(0));
    }
}

