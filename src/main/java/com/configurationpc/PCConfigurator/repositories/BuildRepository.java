package com.configurationpc.PCConfigurator.repositories;

import com.configurationpc.PCConfigurator.models.Build;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildRepository extends JpaRepository<Build, Integer> {
}
