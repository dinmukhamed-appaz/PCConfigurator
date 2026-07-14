package com.configurationpc.PCConfigurator.Serivices;

import com.configurationpc.PCConfigurator.models.Build;
import com.configurationpc.PCConfigurator.models.components.Components;
import com.configurationpc.PCConfigurator.repositories.BuildRepository;
import com.configurationpc.PCConfigurator.repositories.ComponentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})

public class BuildService {

    private final ComponentRepository componentRepository;

    private final BuildRepository buildRepository;

    private final CheckerService checkerService;


    public Build createBuild(List<Integer> componentIds) {

        List<Components> components = componentRepository.findAllById(componentIds);

        List<String> issues = checkerService.check(components);

        Build build = new Build();
        build.setComponents(components);

        build.setStatus(issues.isEmpty());


        return buildRepository.save(build);
    }
}
