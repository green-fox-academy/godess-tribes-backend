package com.greenfoxacademy.goddesstribesbackend.controllers;

import com.greenfoxacademy.goddesstribesbackend.models.dtos.BuildingsDTO;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.TokenDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuildingController {

  @GetMapping("/kingdom/buildings")
  public ResponseEntity<Object> mockListOfBuildings(@RequestBody TokenDTO tokenDTO) {
    return ResponseEntity.status(400).body(new BuildingsDTO());
  }
}
