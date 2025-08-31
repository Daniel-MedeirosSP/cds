package com.medeirosdaniel.cds.app.service;

import com.medeirosdaniel.cds.app.dto.DependenciesDto;
import com.medeirosdaniel.cds.app.to.DependenciesTo;
import org.springframework.stereotype.Service;

@Service
public class DependenciesService {

    public DependenciesDto check(DependenciesTo dependenciesTo) {
        validateDependencies(dependenciesTo);
        return null;
    }

    public void validateDependencies(DependenciesTo dependenciesTo) {

    }
}
