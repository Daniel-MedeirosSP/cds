package com.medeirosdaniel.cds.infra.endoflife.java;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JavaJsonObject {

    @JsonProperty("cycle")
    private String cycle;
    @JsonProperty("releaseDate")
    private String releaseDate;
    @JsonProperty("eol")
    private String eol;
    @JsonProperty("latest")
    private String latest;
    @JsonProperty("latestReleaseDate")
    private String latestReleaseDate;
    @JsonProperty("link")
    private String link;
    @JsonProperty("lts")
    private Boolean lts;
    @JsonProperty("extendedSupport")
    private String extendedSupport;

    /*
        https://endoflife.date/api/oracle-jdk.json
     */

}
