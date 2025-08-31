package com.medeirosdaniel.cds.app.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DependencyTo {

    @JsonProperty("name")
    private String name;
    @JsonProperty("version")
    private String version;

}
