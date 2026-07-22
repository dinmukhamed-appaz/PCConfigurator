package com.configurationpc.PCConfigurator.validators;

import com.configurationpc.PCConfigurator.models.components.Components;
import com.configurationpc.PCConfigurator.models.components.Motherboard;
import com.configurationpc.PCConfigurator.models.components.Ram;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RamTypeValidatorTest {
    private final RamTypeValidator ramTypeValidator = new RamTypeValidator();

    @Test
    void ramTypeMatchValidationTest() {
        Motherboard motherboard = new Motherboard();
        motherboard.setRamType("DDR4");

        Ram ram = new Ram();
        ram.setTypeRam("DDR4");

        List<Components> components = List.of(motherboard, ram);
        List<String> issues = new ArrayList<>();

        ramTypeValidator.validate(components, issues);

        assertTrue(issues.isEmpty());
    }

    @Test
    void ramTypeNotMatchValidationTest() {
        Motherboard motherboard = new Motherboard();
        motherboard.setRamType("DDR4");

        Ram ram = new Ram();
        ram.setTypeRam("DDR5");

        List<Components> components = List.of(motherboard, ram);
        List<String> issues = new ArrayList<>();
        ramTypeValidator.validate(components, issues);

        assertEquals(1, issues.size());
        assertEquals("Ram type do not match with motherboard", issues.get(0));
    }
}
