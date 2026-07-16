package com.configurationpc.PCConfigurator.Controllers;


import com.configurationpc.PCConfigurator.Serivices.BuildService;
import com.configurationpc.PCConfigurator.models.Build;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/builds")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BuildController {

    private final BuildService buildService;

    @PostMapping("")
    public Build create() {
        Build newBuild = buildService.createBuild();
        return newBuild;
    }

    @PostMapping("/{id}/components")
    public Build addComponent(@PathVariable int buildId, @RequestBody int componentId) {
        return buildService.addComponents(buildId, componentId);
    }

    @GetMapping("/{id}")
    public Build getBuildById(@PathVariable int id){
        return buildService.showBuildById(id);
    }

}
