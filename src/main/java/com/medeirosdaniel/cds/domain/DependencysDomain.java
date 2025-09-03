package com.medeirosdaniel.cds.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DependencysDomain {

    private String technology;
    private String technologyVersion;
    private Boolean isObsoleted = Boolean.FALSE;
    private String technologyVersionLatest;
    private String eol;


    private String suggestedLtsVersion; //21
    private String suggestedTechnologyVersion; //21.0.6
    private String suggestedEol;


    private String dependencyManager;
    private List<Dependency> dependencies;
}
