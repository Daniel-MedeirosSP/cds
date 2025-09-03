package com.medeirosdaniel.cds.app.controller;

import com.medeirosdaniel.cds.app.dto.DependenciesDto;
import com.medeirosdaniel.cds.app.service.DependenciesService;
import com.medeirosdaniel.cds.app.to.DependenciesTo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dependencies")
public class DependenciesController {

    private final DependenciesService dependenciesService;

    public DependenciesController(DependenciesService dependenciesService) {
        this.dependenciesService = dependenciesService;
    }

    @PostMapping("/check")
    public ResponseEntity<DependenciesDto> checkDependencies(@RequestBody DependenciesTo dependenciesTo) {
        return ResponseEntity.ok((dependenciesService.checkValues(dependenciesTo)));
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("API is working!");
    }
}
