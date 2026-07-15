package com.configurationpc.PCConfigurator.Serivices;

import com.configurationpc.PCConfigurator.exceptions.NotFoundException;
import com.configurationpc.PCConfigurator.models.components.*;
import com.configurationpc.PCConfigurator.repositories.ComponentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ComponentsService {

    private final ComponentRepository componentRepository;

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

    public List<Components> showComponents() {
        return componentRepository.findAll();
    }

    public Components showComponentsById(int id) {
        return componentRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Component",id));
    }



}
