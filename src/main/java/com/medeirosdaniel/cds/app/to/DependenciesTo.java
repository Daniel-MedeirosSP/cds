package com.medeirosdaniel.cds.app.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DependenciesTo {

    @JsonProperty("technology")
    private String technology;
    @JsonProperty("technology_version")
    private String technologyVersion;
    @JsonProperty("dependency_manager")
    private String dependencyManager;
    @JsonProperty("dependencies")
    private List<DependencyTo> dependencies;
}
