package com.medeirosdaniel.cds.infra.depsdev;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PackageKey {

    @SerializedName("system")
    @Expose
    public String system;
    @SerializedName("name")
    @Expose
    public String name;
}
