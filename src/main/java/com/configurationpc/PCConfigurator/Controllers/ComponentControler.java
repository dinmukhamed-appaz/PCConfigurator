package com.configurationpc.PCConfigurator.Controllers;

import com.configurationpc.PCConfigurator.models.Components;
import com.configurationpc.PCConfigurator.repositories.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/components")
public class ComponentControler {

    @Autowired
    private ComponentRepository componentRepository;

    @GetMapping
    public List<Components> getComponents() {
        return componentRepository.findAll();
    }

    @PostMapping
    public Components createComponent(@RequestBody Components components) {
        return componentRepository.save(components);
    }

}
