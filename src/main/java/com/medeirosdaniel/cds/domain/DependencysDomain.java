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

    private String dependencyManager;

    private List<Dependency> dependencies;
}
