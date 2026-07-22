package com.configurationpc.PCConfigurator.validators;

import com.configurationpc.PCConfigurator.models.components.Components;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompabilityValidatorTest {

    static class Cpu extends Components{}

    @Test
    void testFind(){
        Cpu myCpu = new Cpu();

        List<Components> list = List.of(myCpu);
        Components result = CompabilityValidator.find(list, Cpu.class);
        assertNotNull(result);
    }

    @Test
     void testMatchesAny(){
        boolean match = CompabilityValidator.matchesAny("AM4, AM5", "AM4");
        assertTrue(match);

        boolean noMatch = CompabilityValidator.matchesAny("AM4, AM5", "LGA1700");
        assertFalse(noMatch);
    }





}