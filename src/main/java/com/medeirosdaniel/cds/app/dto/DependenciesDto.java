package com.medeirosdaniel.cds.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DependenciesDto {

    private String technology;
    private String dependencyManager;
    private List<DependencyDto> dependencies;
}
