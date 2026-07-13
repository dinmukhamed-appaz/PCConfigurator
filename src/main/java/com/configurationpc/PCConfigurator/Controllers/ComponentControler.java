package com.configurationpc.PCConfigurator.Controllers;

import com.configurationpc.PCConfigurator.Serivices.ComponentsService;
import com.configurationpc.PCConfigurator.models.components.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/components")
public class ComponentControler {

    @Autowired
    private ComponentsService componentsService;

    @PostMapping("/cpu")
    public Cpu createComponent(@RequestBody Cpu cpu) {
        Cpu created = componentsService.createComponent(cpu);
        return created;
    }

    @PostMapping("/motherboard")
    public Motherboard createComponent(@RequestBody Motherboard motherboard) {
        Motherboard created = componentsService.createComponent(motherboard);
        return created;
    }

    @PostMapping("/gpu")
    public Gpu createComponent(@RequestBody Gpu gpu) {
        Gpu created = componentsService.createComponent(gpu);
        return created;
    }

    @PostMapping("/psu")
    public Psu createComponent(@RequestBody Psu psu) {
        Psu created = componentsService.createComponent(psu);
        return created;
    }

    @PostMapping("/ram")
    public Ram createComponent(@RequestBody Ram ram) {
        Ram created = componentsService.createComponent(ram);
        return created;
    }

    @PostMapping("/cooler")
    public Cooler createComponent(@RequestBody Cooler cooler) {
        Cooler created = componentsService.createComponent(cooler);
        return created;
    }

    @PostMapping("/case")
    public Case createComponent(@RequestBody Case cases) {
        Case created = componentsService.createComponent(cases);
        return created;
    }

    @GetMapping("")
    public List<Components> showComponets(Components components) {
        return componentsService.showComponents(components);
    }

}
