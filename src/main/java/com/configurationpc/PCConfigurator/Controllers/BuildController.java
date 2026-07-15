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

    @PostMapping("/create")
    public Build createBuild(@RequestBody List<Integer> componentIds) {
        Build newBuild = buildService.createBuild(componentIds);
        return newBuild;
    }

    @GetMapping("")
    public List<Build> getBuild() {
        return buildService.showBuild();
    }

    @GetMapping("/{id}")
    public Build getBuildById(@RequestParam int id){
        return buildService.showBuildById(id);
    }

}
