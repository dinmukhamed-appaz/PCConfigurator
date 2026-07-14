package com.configurationpc.PCConfigurator.Serivices;

import com.configurationpc.PCConfigurator.models.components.*;
import com.configurationpc.PCConfigurator.repositories.BuildRepository;
import com.configurationpc.PCConfigurator.repositories.ComponentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CheckerService {

    private final ComponentRepository componentRepository;

    private final BuildRepository buildRepository;


    public List<String> check(List<Components> components) {
        List<String> issues = new ArrayList<>();

        if (components == null) {
            issues.add("No component found");
        }

        Cpu cpu = find(components, Cpu.class);
        Gpu gpu = find(components, Gpu.class);
        Psu psu = find(components, Psu.class);
        Ram ram = find(components, Ram.class);
        Motherboard motherboard = find(components, Motherboard.class);
        Cooler cooler = find(components, Cooler.class);
        Case casefing = find(components, Case.class);


        if (cpu != null && motherboard != null ) {

            String cpuSocket = cpu.getSocket();
            String motherboardSocket = motherboard.getSocket();

            try{
                if(!cpuSocket.contains(motherboardSocket)) {
                    issues.add("Cpu socket and motherboard socket are different");
                }
            }
            catch (Exception e){
                issues.add("Can not get socket from Cpu or Motherboard");
            }
        }

        if (cpu != null && motherboard != null && cooler != null) {
            String cpuSocket = cpu.getSocket();
            String motherboardSocket = motherboard.getSocket();
            String coolerSocket = cooler.getSupportedSockets();

            try {
                if (!coolerSocket.contains(motherboardSocket)
                        || !coolerSocket.contains(cpuSocket)) {
                    issues.add("Sockets does not match");
                }
            }
            catch (Exception e){
                issues.add("Can not get sockets from Cooler");
        }
        }




        return issues;

    }


    private <T extends Components> T find(List<Components> components, Class<T> type) {
        for (Components component : components) {
            if(type.isInstance(component)){
                return (T) component;
            }
        }
        return null;
    }

}
