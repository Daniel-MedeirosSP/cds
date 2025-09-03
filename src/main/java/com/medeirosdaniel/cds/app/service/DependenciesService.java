package com.medeirosdaniel.cds.app.service;

import com.medeirosdaniel.cds.app.dto.DependenciesDto;
import com.medeirosdaniel.cds.app.repositories.CheckDependenciesRepository;
import com.medeirosdaniel.cds.app.to.DependenciesTo;
import com.medeirosdaniel.cds.app.to.DependencyTo;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DependenciesService {

    @Value("${endoflife.url}")
    private String endOfLifeUrl;

    @Value("${endoflife.java}")
    private String java;


    @Value("${depsdev.url}")
    private String depsDevUrl;

    @Value("${depsdev.packages}")
    private String depsDevPackages;


    private final Logger logger = LoggerFactory.getLogger(DependenciesService.class);
    private final ModelMapper modelMapper = new ModelMapper();
    private CheckDependenciesRepository checkDependenciesRepository;
    public DependenciesService(CheckDependenciesRepository checkDependenciesRepository) {
        this.checkDependenciesRepository = checkDependenciesRepository;
    }

    public DependenciesDto checkValues(DependenciesTo dependenciesTo) {
        String technologyUrl = "";
        String dependenciesUrl = "";
        validateDependencies(dependenciesTo);

        switch(dependenciesTo.getTechnology().toLowerCase()) {
            case "java":
                technologyUrl = endOfLifeUrl + java;
                dependenciesUrl = depsDevUrl + dependenciesTo.getDependencyManager() + depsDevPackages;
                break;
            default:
                logger.error("Technology not supported: {}", dependenciesTo.getTechnology());
                throw new IllegalArgumentException("Technology not supported: " + dependenciesTo.getTechnology());
        }

        var cds = checkDependenciesRepository.checkTechonologyVersion(technologyUrl,
                dependenciesTo);
        var cds2 = checkDependenciesRepository.checkDependenciesVersion(dependenciesUrl, cds, dependenciesTo);

        return modelMapper.map(cds2, DependenciesDto.class);
    }

    public void validateDependencies(DependenciesTo dependenciesTo) {
        logger.info("Validating dependencies: {}", dependenciesTo);
        if(dependenciesTo.getTechnology() == null ||
                dependenciesTo.getTechnology().isEmpty() ||
                dependenciesTo.getTechnology().isBlank()) {
            logger.error("Technology must be provided.");
            throw new IllegalArgumentException("Technology must be provided.");
        }
        if(dependenciesTo.getTechnologyVersion() == null ||
                dependenciesTo.getTechnologyVersion().isEmpty() ||
                dependenciesTo.getTechnologyVersion().isBlank()) {
            logger.error("Technology version must be provided.");
            throw new IllegalArgumentException("Technology version must be provided.");
        }
        if(dependenciesTo.getDependencyManager() == null ||
                dependenciesTo.getDependencyManager().isEmpty() ||
                dependenciesTo.getDependencyManager().isBlank()) {
            logger.error("Dependency manager must be provided.");
            throw new IllegalArgumentException("Dependency manager must be provided.");
        }

        if(dependenciesTo.getDependencies().isEmpty()) {
            logger.error("Dependencies list must be provided.");
            throw new IllegalArgumentException("Dependencies list must be provided.");
        }

        validateDependencysList(dependenciesTo.getDependencies());

    }

    public void validateDependencysList(List<DependencyTo> dependencies) {
        for(DependencyTo dependency : dependencies) {
            if(dependency.getName() == null ||
                    dependency.getName().isEmpty() ||
                    dependency.getName().isBlank()) {
                logger.error("Dependency name must be provided.");
                throw new IllegalArgumentException("Dependency name must be provided.");
            }
            if(dependency.getVersion() == null ||
                    dependency.getVersion().isEmpty() ||
                    dependency.getVersion().isBlank()) {
                logger.error("Dependency version must be provided.");
                throw new IllegalArgumentException("Dependency version must be provided.");
            }
        }
    }
}
