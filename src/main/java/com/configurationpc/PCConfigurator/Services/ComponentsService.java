package com.configurationpc.PCConfigurator.Services;

import com.configurationpc.PCConfigurator.dto.ComponentsRequestDto;
import com.configurationpc.PCConfigurator.exceptions.NotIdFoundException;
import com.configurationpc.PCConfigurator.models.components.*;
import com.configurationpc.PCConfigurator.repositories.ComponentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ComponentsService {

    private final ComponentRepository componentRepository;

    private final ComponentCreateService componentCreateService;



    public List<Components> showComponents() {
        return componentRepository.findAll();
    }

    public Components showComponentsById(int id) {
        return componentRepository.findById(id)
                .orElseThrow(() ->
                        new NotIdFoundException("Component",id));
    }

    public Components createComponent(ComponentsRequestDto requestDto) {
        Components component = componentCreateService.create(requestDto);
        return componentRepository.save(component);
    }


    public Components updateComponent(int id, ComponentsRequestDto requestDto) {
        Components current = componentRepository.findById(id).orElseThrow(
                () -> new NotIdFoundException("Component",id)
        );

        Components updated = componentCreateService.create(requestDto);
        updated.setId(current.getId());
        return componentRepository.save(updated);

    }

    public Components deleteComponent(int id) {
        Components component = componentRepository.findById(id).orElseThrow(
                () -> new NotIdFoundException("Component",id)
        );

        componentRepository.delete(component);
        return component;
    }



}
