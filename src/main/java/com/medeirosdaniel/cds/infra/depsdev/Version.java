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

public class Version {

    @SerializedName("versionKey")
    @Expose
    public VersionKey versionKey;
    @SerializedName("publishedAt")
    @Expose
    public String publishedAt;
    @SerializedName("isDefault")
    @Expose
    public boolean isDefault;

}
