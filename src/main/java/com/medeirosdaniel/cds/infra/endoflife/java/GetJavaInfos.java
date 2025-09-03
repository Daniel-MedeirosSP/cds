package com.medeirosdaniel.cds.infra.endoflife.java;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetJavaInfos {

    private List<JavaJsonObject> javaList;
}
