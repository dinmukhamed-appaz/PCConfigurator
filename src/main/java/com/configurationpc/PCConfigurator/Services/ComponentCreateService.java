package com.configurationpc.PCConfigurator.Services;

import com.configurationpc.PCConfigurator.dto.ComponentsRequestDto;
import com.configurationpc.PCConfigurator.models.components.*;
import org.springframework.stereotype.Service;

@Service
public class ComponentCreateService {
    public Components create(ComponentsRequestDto requestDto) {
        Components component = switch (requestDto.getCategory()){
            case "Cpu" -> buildCpu(requestDto);
            case "Gpu" -> buildGpu(requestDto);
            case "Psu" -> buildPsu(requestDto);
            case "Ram" -> buildRam(requestDto);
            case "Motherboard" -> buildMotherboard(requestDto);
            case "Cooler" -> buildCooler(requestDto);
            case "Case" -> buildCase(requestDto);
            default -> throw new IllegalArgumentException("Invalid component category");
        };

        component.setName(requestDto.getName());
        component.setPrice(requestDto.getPrice());

        return component;
    }

    private Cpu buildCpu(ComponentsRequestDto requestDto) {
        Cpu cpu = new Cpu();
        cpu.setSocket(requestDto.getSocket());
        cpu.setTdp(requestDto.getTdp());

        return cpu;
    }

    private Gpu buildGpu(ComponentsRequestDto requestDto) {
        Gpu gpu = new Gpu();
        gpu.setPowerConsumption(requestDto.getPowerConsumption());
        gpu.setPowerConnectors(requestDto.getAvailableConnectors());
        gpu.setLengthGpu(requestDto.getLengthGpu());

        return gpu;
    }

    private Psu buildPsu(ComponentsRequestDto requestDto) {
        Psu psu = new Psu();
        psu.setTotalPower(requestDto.getTotalPower());
        psu.setAvailableConnectors(requestDto.getAvailableConnectors());

        return psu;
    }

    private Ram buildRam(ComponentsRequestDto requestDto) {
        Ram ram = new Ram();
        ram.setTypeRam(requestDto.getRamType());
        ram.setMemory(requestDto.getMemory());

        return ram;
    }

    private Motherboard buildMotherboard(ComponentsRequestDto requestDto) {
        Motherboard motherboard = new Motherboard();
        motherboard.setSocket(requestDto.getSocket());
        motherboard.setRamType(requestDto.getRamType());
        motherboard.setFormFactor(requestDto.getFormFactor());

        return motherboard;
    }

    private Cooler buildCooler(ComponentsRequestDto requestDto) {
        Cooler cooler = new Cooler();
        cooler.setSupportedSockets(requestDto.getSupportedSockets());
        cooler.setMaxTdp(requestDto.getMaxTdp());
        cooler.setHeightCooler(requestDto.getHeightCooler());

        return cooler;
    }

    private Case buildCase(ComponentsRequestDto requestDto) {
        Case cases = new Case();
        cases.setSupportedFormFactors(requestDto.getSupportedFormFactor());
        cases.setMaxGpuLength(requestDto.getMaxGpuLength());
        cases.setMaxCoolerHeight(requestDto.getMaxCoolerHeight());

        return cases;
    }


}
