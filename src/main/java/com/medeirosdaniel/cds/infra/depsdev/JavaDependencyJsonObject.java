package com.medeirosdaniel.cds.infra.depsdev;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JavaDependencyJsonObject {


    private String system;
    //https://api.deps.dev/v3/systems/maven/packages/com.google.code.gson:gson
    //https://api.deps.dev/v3/systems/maven/packages/org.junit.jupiter:junit-jupiter-api
}
