package com.configurationpc.PCConfigurator.validators;


import com.configurationpc.PCConfigurator.models.components.*;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class SocketValidator implements CompabilityValidator {


    @Override
    public void validate(List<Components> components, List<String> issues){
        Cpu cpu = CompabilityValidator.find(components, Cpu.class);
        Motherboard motherboard = CompabilityValidator.find(components, Motherboard.class);
        Cooler cooler = CompabilityValidator.find(components, Cooler.class);


        if (cpu != null && motherboard != null) {
            String cpuSocket = cpu.getSocket();
            String motherboardSocket = motherboard.getSocket();
            if (!cpuSocket.equalsIgnoreCase(motherboardSocket)) {
                issues.add("Cpu socket and motherboard sockets do not match");
            }
        }

        if(cpu != null && cooler != null){
            String cpuSocket = cpu.getSocket();
            String coolerSockets = cooler.getSupportedSockets();

            if(!CompabilityValidator.matchesAny(coolerSockets, cpuSocket)){
                issues.add("Cooler sockets and cpu sockets do not match");
            }
        }

        if(motherboard != null && cooler != null){
            String motherboardSocket = motherboard.getSocket();
            String coolerSockets = cooler.getSupportedSockets();
            if(!CompabilityValidator.matchesAny(coolerSockets, motherboardSocket)){
                issues.add("Cooler sockets and motherboard sockets do not match");
            }
        }
        }




    }


