package com.configurationpc.PCConfigurator.repositories;


import com.configurationpc.PCConfigurator.models.components.Components;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComponentRepository extends JpaRepository<Components, Integer> {
}
