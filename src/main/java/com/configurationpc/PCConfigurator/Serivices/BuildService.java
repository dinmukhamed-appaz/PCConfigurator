package com.configurationpc.PCConfigurator.Serivices;

import com.configurationpc.PCConfigurator.exceptions.IncompatibilityIssuesException;
import com.configurationpc.PCConfigurator.exceptions.NotFoundException;
import com.configurationpc.PCConfigurator.models.Build;
import com.configurationpc.PCConfigurator.models.components.Components;
import com.configurationpc.PCConfigurator.repositories.BuildRepository;
import com.configurationpc.PCConfigurator.repositories.ComponentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})

public class BuildService {

    private final ComponentRepository componentRepository;

    private final BuildRepository buildRepository;

    private final CheckerService checkerService;


    public Build createBuild(){
        Build build = new Build();
        build.setStatus(true);
        return buildRepository.save(build);
    }

    public Build addComponents(int buildId, int componentId){
        Build build = buildRepository.findById(buildId)
                .orElseThrow(() ->
                        new NotFoundException("Build", buildId));

        Components components = componentRepository.findById(componentId)
                .orElseThrow(() ->
                        new NotFoundException("Components", componentId));

        List<Components> updatedComponents = new ArrayList<>(build.getComponents());
        updatedComponents.add(components);

        List<String> issues = checkerService.check(updatedComponents);

        if(!issues.isEmpty()) {
            throw new IncompatibilityIssuesException(issues);
        }

        build.setComponents(updatedComponents);
        build.setTotalPrice(checkerService.totalPrice(updatedComponents));

        return buildRepository.save(build);

    }


    public Build showBuildById(int id){
        return buildRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Build", id));
    }
}
