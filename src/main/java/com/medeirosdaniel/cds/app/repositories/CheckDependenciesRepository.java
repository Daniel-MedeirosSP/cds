package com.medeirosdaniel.cds.app.repositories;

import com.google.gson.GsonBuilder;
import com.medeirosdaniel.cds.app.to.DependenciesTo;
import com.medeirosdaniel.cds.app.to.DependencyTo;
import com.medeirosdaniel.cds.domain.Dependency;
import com.medeirosdaniel.cds.domain.DependencysDomain;
import com.medeirosdaniel.cds.infra.depsdev.Depsdev;
import com.medeirosdaniel.cds.infra.depsdev.Version;
import com.medeirosdaniel.cds.infra.endoflife.java.JavaJsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Repository
public class CheckDependenciesRepository {

    private final Logger logger = LoggerFactory.getLogger(CheckDependenciesRepository.class);
    private final AccessApi accessApi = new AccessApi();
    private GsonBuilder gsonBuilder = new GsonBuilder();

    public DependencysDomain checkTechonologyVersion(String url, DependenciesTo dependenciesTo) {
        logger.error("Checking technology version: {} {}", dependenciesTo.getTechnology(), dependenciesTo.getTechnologyVersion());
        var request = accessApi.Request(url);
        var dependencysDomain = new DependencysDomain();
        LocalDate today = LocalDate.now();


        //todo -> refatorar para uma classe de tratamento de para cada tecnologia EX: Java, Python, NodeJS
        List<JavaJsonObject> getJavaInfos = List.of(gsonBuilder.create().fromJson(request, JavaJsonObject[].class));
        var checkVersion = getJavaInfos.stream().filter(
                version -> version.getCycle().equals(dependenciesTo.getTechnologyVersion())).findFirst().orElseThrow();

        var ltsVersions = getJavaInfos.stream().filter(
                lts -> lts.getLts() &&
                        futureEol(lts.getEol(), today)).toList();
        var ltsMaxVersion = ltsVersions.stream().max(
                Comparator.comparingInt(version ->
                        Integer.parseInt(version.getCycle()))).orElseThrow();


        dependencysDomain.setTechnology(dependenciesTo.getTechnology());
        dependencysDomain.setTechnologyVersion(dependenciesTo.getTechnologyVersion());
        dependencysDomain.setIsObsoleted(checkVersion.getExtendedSupport().equals("false") ? Boolean.TRUE : Boolean.FALSE);
        dependencysDomain.setTechnologyVersionLatest(checkVersion.getLatest());
        dependencysDomain.setDependencyManager(dependenciesTo.getDependencyManager());
        dependencysDomain.setEol(checkVersion.getEol());

        dependencysDomain.setSuggestedLtsVersion(ltsMaxVersion.getCycle());
        dependencysDomain.setSuggestedTechnologyVersion(ltsMaxVersion.getLatest());
        dependencysDomain.setSuggestedEol(ltsMaxVersion.getEol());

        return dependencysDomain;
    }


    public Boolean futureEol(String eol, LocalDate today) {
        if (eol == null || eol.isEmpty() || eol.isBlank() || eol.equals("false")) {
            return Boolean.FALSE;
        }
        var eolDate = LocalDate.parse(eol);
        return eolDate.isAfter(today);
    }

    public DependencysDomain checkDependenciesVersion(String dependenciesUrl, DependencysDomain cds, DependenciesTo dependenciesTo) {
        logger.info("Checking dependencies version: {}", dependenciesTo.getDependencies());
        List<Dependency> dependencies = new ArrayList<>();

        LocalDate today = LocalDate.now();
        for (var dependency : dependenciesTo.getDependencies()) {
            var request = accessApi.Request(dependenciesUrl + dependency.getName());
            var depsdev = gsonBuilder.create().fromJson(request, Depsdev.class);
            var filtered = depsdev.getVersions().stream()
                    .filter(version -> version.getPublishedAt() != null)
                    .filter(version -> !version.getVersionKey().getVersion().toLowerCase().matches(".*-(m|rc)\\d+$"))
                    .min(Comparator.comparing(version ->
                    {
                        return Duration.between(Instant.parse(version.getPublishedAt()), Instant.now()).abs();
                    })).orElseThrow();

            dependencies.add(new Dependency(
                    dependency.getName(),
                    dependency.getVersion(),
                    checkObsoleted(dependency, filtered),
                    filtered.getVersionKey().getVersion(),
                    filtered.getPublishedAt()
            ));
        }
        cds.setDependencies(dependencies);
        return cds;
    }

    public Boolean checkObsoleted(DependencyTo dependency, Version filtered) {
        var checkVersion = dependency.getVersion().split("\\.");
        var filteredMajorVersion = filtered.getVersionKey().getVersion().split("\\.")[0];
        var diffMajorVersion = Integer.parseInt(filteredMajorVersion) - Integer.parseInt(checkVersion[0]);

        if(diffMajorVersion >= 2) {
            return Boolean.TRUE;
        }

        if(checkVersion.length < 2) {
            return Boolean.TRUE;
        }else{
            var checkPathVersion = dependency.getVersion().split("\\.")[1];
            var filteredPathVersion = filtered.getVersionKey().getVersion().split("\\.")[1];
            var diffPathVersion = Integer.parseInt(filteredPathVersion) - Integer.parseInt(checkPathVersion);

            if(diffPathVersion > 5) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
}
