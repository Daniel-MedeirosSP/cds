package com.medeirosdaniel.cds.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DependencyDto {

    private String name;

    private String version;

    private Boolean isObsoleted = Boolean.FALSE;

    private String suggestedVersion;

    private String versionLatest;

}
