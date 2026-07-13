package com.configurationpc.PCConfigurator.Serivices;

import com.configurationpc.PCConfigurator.models.components.Components;
import com.configurationpc.PCConfigurator.models.components.Cooler;
import com.configurationpc.PCConfigurator.models.components.Cpu;
import com.configurationpc.PCConfigurator.models.components.Motherboard;
import com.configurationpc.PCConfigurator.repositories.BuildRepository;
import com.configurationpc.PCConfigurator.repositories.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CheckerService {

    @Autowired
    private ComponentRepository componentRepository;

    @Autowired
    private BuildRepository buildRepository;

    public CheckerService(ComponentRepository componentRepository, BuildRepository buildRepository) {
        this.componentRepository = componentRepository;
        this.buildRepository = buildRepository;
    }


    public List<String> check(List<Components> components) {
        List<String> issues = new ArrayList<>();

        if (components == null) {
            issues.add("No component found");
        }

        Cpu cpu = null;
        Motherboard motherboard = null;
        Cooler cooler = null;


        for (Components comp : components) {
            if (comp instanceof Cpu) {
                cpu = (Cpu) comp;
            } else if (comp instanceof Motherboard) {
                motherboard = (Motherboard) comp;
            } else if (comp instanceof Cooler) {
                cooler = (Cooler) comp;
            }
        }

        if (cpu != null && motherboard != null ) {
            try{
                if(!cpu.getSocket().equalsIgnoreCase(motherboard.getSocket())){
                    issues.add("Cpu socket and motherboard socket are different");
                }
            }
            catch (Exception e){
                issues.add("Can not get socket from Cpu or Motherboard");
            }
        }

        if (cpu != null && motherboard != null && cooler != null) {
            try {
                if (!cooler.getSupportedSockets().equalsIgnoreCase(motherboard.getSocket())
                        || !cooler.getSupportedSockets().equalsIgnoreCase(cpu.getSocket())) {
                    issues.add("Sockets does not match");
                }
            }
            catch (Exception e){
                issues.add("Can not get sockets from Cooler");
        }
        }



        return issues;

    }
}
