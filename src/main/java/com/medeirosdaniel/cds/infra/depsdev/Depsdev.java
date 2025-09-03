package com.medeirosdaniel.cds.infra.depsdev;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class Depsdev {
    @SerializedName("packageKey")
    @Expose
    public PackageKey packageKey;
    @SerializedName("versions")
    @Expose
    public List<Version> versions;
}
