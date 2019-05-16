package com.greenfoxacademy.goddesstribesbackend.controllers;

import com.greenfoxacademy.goddesstribesbackend.models.dtos.ErrorMessage;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.ResourceDTO;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.TokenDTO;
import com.greenfoxacademy.goddesstribesbackend.security.jwt.JWTUtility;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

  @GetMapping("/kingdom/resources")
  public ResponseEntity<Object> mockListResources(@RequestBody TokenDTO tokenDTO) {
    if (JWTUtility.parseToken(tokenDTO.getToken()) == null) {
      return ResponseEntity.status(400).body(new ErrorMessage("Invalid token"));
    }
    return ResponseEntity.status(200).body(new ResourceDTO());
  }
}
