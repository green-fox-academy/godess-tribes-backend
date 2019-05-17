package com.greenfoxacademy.goddesstribesbackend.controllers;


import com.greenfoxacademy.goddesstribesbackend.models.MockData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

  @GetMapping("/kingdom/resources")
  public ResponseEntity<Object> mockListResources() {
    return ResponseEntity.status(200).body(MockData.resourcesDTO);
  }

}
