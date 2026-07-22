package com.configurationpc.PCConfigurator.validators;

import com.configurationpc.PCConfigurator.models.components.Components;
import com.configurationpc.PCConfigurator.models.components.Cooler;
import com.configurationpc.PCConfigurator.models.components.Cpu;
import com.configurationpc.PCConfigurator.models.components.Motherboard;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SocketValidatorTest {
    private final SocketValidator socketValidator = new SocketValidator();

    @Test
    void socketMatchValidatorTest(){
        Cpu cpu = new Cpu();
        cpu.setSocket("AM5");

        Motherboard motherboard = new Motherboard();
        motherboard.setSocket("AM5");

        Cooler cooler = new Cooler();
        cooler.setSupportedSockets("AM4, AM5, LGA1700");

        List<Components> cpuMother = List.of(cpu, motherboard);
        List<Components> cpuCooler = List.of(motherboard);
        List<Components> motherboardCooler = List.of(motherboard);

        List<String> issues = new ArrayList<>();

        socketValidator.validate(cpuMother, issues);
        socketValidator.validate(cpuCooler, issues);
        socketValidator.validate(motherboardCooler, issues);

        assertTrue(issues.isEmpty());

    }

    @Test
    void socketNotMatchValidatorTest(){
        Cpu cpu = new Cpu();
        cpu.setSocket("LGA1700");

        Motherboard motherboard = new Motherboard();
        motherboard.setSocket("AM5");

        Cooler cooler = new Cooler();
        cooler.setSupportedSockets("AM4");

        List<Components> cpuMother = List.of(cpu, motherboard);
        List<Components> cpuCooler = List.of(cpu, cooler);
        List<Components> motherboardCooler = List.of(motherboard , cooler);

        List<String> issues = new ArrayList<>();
        socketValidator.validate(cpuMother, issues);
        socketValidator.validate(cpuCooler, issues);
        socketValidator.validate(motherboardCooler, issues);

        assertEquals(3, issues.size());
        assertEquals("Cpu socket and motherboard sockets do not match", issues.get(0));
        assertEquals("Cooler sockets and cpu sockets do not match", issues.get(1));
        assertEquals("Cooler sockets and motherboard sockets do not match", issues.get(2));

    }
}
