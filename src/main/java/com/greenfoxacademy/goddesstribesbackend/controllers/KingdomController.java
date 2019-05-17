package com.greenfoxacademy.goddesstribesbackend.controllers;

import com.greenfoxacademy.goddesstribesbackend.models.dtos.KingdomDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KingdomController {

  @GetMapping("/kingdom")
  public ResponseEntity<Object> findOwnKingdom() {

    return ResponseEntity.status(200).body(new KingdomDTO());
  }

}
