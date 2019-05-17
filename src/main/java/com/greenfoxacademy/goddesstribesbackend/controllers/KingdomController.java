package com.greenfoxacademy.goddesstribesbackend.controllers;

import com.greenfoxacademy.goddesstribesbackend.models.MockData;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.ErrorMessage;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.KingdomNameDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class KingdomController {

  @GetMapping("/kingdom")
  public ResponseEntity<Object> findOwnKingdom() {
    return ResponseEntity.status(200).body(MockData.kingdomDTO);
  }

  @PutMapping("/kingdom")
  public ResponseEntity<Object>mockChangeKingdomName(
            @RequestBody(required =  false) KingdomNameDTO kingdomNameDTO){

    if (kingdomNameDTO.getName() == null){
      return ResponseEntity.status(400).body(new ErrorMessage("Missing parameter(s): <name>!"));
    }

    MockData.kingdomDTO.setKingdomName(kingdomNameDTO.getName());
    return ResponseEntity.status(200).body(MockData.kingdomDTO);
  }

  @GetMapping("/kingdom/{id}")
  public ResponseEntity<Object> mockRenderKingdom (@PathVariable Long id){
    if (MockData.kingdomDTO.getId() == id){
      return ResponseEntity.status(200).body(MockData.kingdomDTO);
    }
    return ResponseEntity.status(404).body(new ErrorMessage("Id not found"));
  }

}
