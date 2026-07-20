package com.configurationpc.PCConfigurator.Controllers;

import com.configurationpc.PCConfigurator.Services.ComponentsService;
import com.configurationpc.PCConfigurator.dto.ComponentsRequestDto;
import com.configurationpc.PCConfigurator.models.components.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/components")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})

public class ComponentController {

    private final ComponentsService componentsService;

    @GetMapping("")
    public List<Components> showComponents() {
        return componentsService.showComponents();
    }

    @GetMapping("/{id}")
    public Components showComponentsById(@PathVariable int id){
        return componentsService.showComponentsById(id);
    }

    @PostMapping("")
    public Components createComponent(@RequestBody ComponentsRequestDto requestDto) {
        return componentsService.createComponent(requestDto);
    }

    @PutMapping("/{id}")
    public Components updateComponent(@PathVariable int id, @RequestBody ComponentsRequestDto requestDto) {
        return componentsService.updateComponent(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public Components deleteComponent(@PathVariable int id) {
        return componentsService.deleteComponent(id);
    }



}
