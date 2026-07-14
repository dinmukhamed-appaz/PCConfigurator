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
        return componentsService.createComponent(cpu);
    }

    @PostMapping("/motherboard")
    public Motherboard createComponent(@RequestBody Motherboard motherboard) {
        return componentsService.createComponent(motherboard);
    }

    @PostMapping("/gpu")
    public Gpu createComponent(@RequestBody Gpu gpu) {
        return componentsService.createComponent(gpu);
    }

    @PostMapping("/psu")
    public Psu createComponent(@RequestBody Psu psu) {
        return componentsService.createComponent(psu);
    }

    @PostMapping("/ram")
    public Ram createComponent(@RequestBody Ram ram) {
        return componentsService.createComponent(ram);
    }

    @PostMapping("/cooler")
    public Cooler createComponent(@RequestBody Cooler cooler) {
        return componentsService.createComponent(cooler);
    }

    @PostMapping("/case")
    public Case createComponent(@RequestBody Case cases) {
        return componentsService.createComponent(cases);
    }

    @GetMapping("")
    public List<Components> showComponets() {
        return componentsService.showComponents();
    }

    @GetMapping("/{id}")
    public Components showComponentsById(@PathVariable int id){
        return componentsService.showComponentsById(id);
    }


}
