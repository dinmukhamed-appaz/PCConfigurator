package com.configurationpc.PCConfigurator.Serivices;


import com.configurationpc.PCConfigurator.models.components.*;
import com.configurationpc.PCConfigurator.repositories.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ComponentsService {

    @Autowired
    private ComponentRepository componentRepository;

    public Cpu createComponent(Cpu cpu) {
        return componentRepository.save(cpu);
    }

    public Gpu createComponent(Gpu gpu) {
        return componentRepository.save(gpu);
    }

    public Psu createComponent(Psu psu) {
        return componentRepository.save(psu);
    }

    public Ram createComponent(Ram ram) {
        return componentRepository.save(ram);
    }

    public Cooler createComponent(Cooler cooler) {
        return componentRepository.save(cooler);
    }

    public Case createComponent(Case cases) {
        return componentRepository.save(cases);
    }

    public Motherboard createComponent(Motherboard motherboard) {
        return componentRepository.save(motherboard);
    }

    public List<Components> showComponents(Components components) {
        return componentRepository.findAll();
    }



}
