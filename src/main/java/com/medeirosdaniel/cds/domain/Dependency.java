package com.medeirosdaniel.cds.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Dependency {

    private String name;

    private String version;

    private String scope;
}
