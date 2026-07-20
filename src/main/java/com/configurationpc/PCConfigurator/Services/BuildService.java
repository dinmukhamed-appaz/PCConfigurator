package com.configurationpc.PCConfigurator.Services;

import com.configurationpc.PCConfigurator.dto.ComponentsRequestDto;
import com.configurationpc.PCConfigurator.exceptions.CategoryAlreadyExistException;
import com.configurationpc.PCConfigurator.exceptions.IncompatibilityIssuesException;
import com.configurationpc.PCConfigurator.exceptions.NotIdFoundException;
import com.configurationpc.PCConfigurator.models.Build;
import com.configurationpc.PCConfigurator.models.components.Components;
import com.configurationpc.PCConfigurator.repositories.BuildRepository;
import com.configurationpc.PCConfigurator.repositories.ComponentRepository;
import com.configurationpc.PCConfigurator.dto.ComponentsRequestDto;

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

    private final RecommendationService recommendationService;

    private final ComponentCreateService componentCreateService;

    public Build createBuild(){
        Build build = new Build();
        build.setStatus(true);
        return buildRepository.save(build);
    }


    public Build addComponents(int buildId, int componentId){
        Build build = buildRepository.findById(buildId)
                .orElseThrow(() ->
                        new NotIdFoundException("Build", buildId));

        Components components = componentRepository.findById(componentId)
                .orElseThrow(() ->
                        new NotIdFoundException("Components", componentId));


        boolean categoryAlreadyExists = build.getComponents()
                .stream()
                .anyMatch(currentComponent -> currentComponent.getClass().equals(components.getClass()));

        if(categoryAlreadyExists){
            throw new CategoryAlreadyExistException("The component of category" + components.getCategory() + "is already exists");
        }

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

    public List<String> getRecommendations(int buildId){
        Build build = showBuildById(buildId);
        return recommendationService.recommend(build.getComponents());
    }

    public List<Build> showAllBuilds(){
        return buildRepository.findAll();
    }


    public Build showBuildById(int id){
        return buildRepository.findById(id)
                .orElseThrow(() ->
                        new NotIdFoundException("Build", id));
    }

    public Build deleteBuildById(int id){
        Build build = showBuildById(id);
        buildRepository.delete(build);
        return build;
    }

    public Build deleteComponentBuild(int buildId, int componentId){
        Build build = showBuildById(buildId);
        build.getComponents().removeIf(component -> component.getId() == componentId);
        return build;
    }

    public Build updateComponentBuild(int buildId, int componentId, ComponentsRequestDto requestDto){
        Build build = showBuildById(buildId);
        Components componentToUpdate = build.getComponents()
                .stream().filter(component -> component.getId() == componentId)
                .findFirst()
                .orElseThrow(() -> new NotIdFoundException("Component", componentId));

        Components updated = componentCreateService.create(requestDto);

        updated.setId(componentToUpdate.getId());

        build.getComponents().remove(componentToUpdate);
        build.getComponents().add(updated);

        return buildRepository.save(build);
    }



}
