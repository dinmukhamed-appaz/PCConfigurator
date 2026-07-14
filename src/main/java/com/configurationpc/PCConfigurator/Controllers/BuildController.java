package com.configurationpc.PCConfigurator.Controllers;


import com.configurationpc.PCConfigurator.Serivices.BuildService;
import com.configurationpc.PCConfigurator.models.Build;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/builds")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BuildController {


    private final BuildService buildService;

    @PostMapping("/create")
    public ResponseEntity<Build> createBuild(@RequestBody List<Integer> componentIds) {
        Build newBuild = buildService.createBuild(componentIds);

        return ResponseEntity.ok(newBuild);
    }
}
