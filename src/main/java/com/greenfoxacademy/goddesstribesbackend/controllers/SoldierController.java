package com.greenfoxacademy.goddesstribesbackend.controllers;

import com.greenfoxacademy.goddesstribesbackend.models.MockData;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.ErrorMessage;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.LevelDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class SoldierController {

  @GetMapping("/kingdom/soldiers")
  public ResponseEntity<Object> mockListOfSoldiers() {
    return ResponseEntity.status(200).body(MockData.soldiersDTO);
  }

  @PostMapping("/kingdom/soldiers")
  public ResponseEntity<Object> mockTrainANewSoldier () {

    if (MockData.gold.getAmount() <= 10) {
      return ResponseEntity.status(409).body(new ErrorMessage("Not enough resource"));
    }
    return ResponseEntity.status(200).body(MockData.soldierDTO);

  }

  @GetMapping("/kingdom/soldiers/{id}")
  public ResponseEntity<Object> mockRenderSoldier(@PathVariable Long id) {

    if (MockData.soldierDTO.getId() == id) {
      return ResponseEntity.status(200).body(MockData.soldierDTO);
    }
    return ResponseEntity.status(404).body(new ErrorMessage("Id not found"));
  }

  @PutMapping("/kingdom/soldiers/{id}")
  public ResponseEntity<Object> mockChangeSoldierLevel(@PathVariable Long id, @RequestBody LevelDTO levelDTO) {

    if (levelDTO.getLevel() == null){
      return ResponseEntity.status(400).body(new ErrorMessage("Missing parameter(s): <level>!"));
    }

    if (MockData.soldierDTO.getId() != id){
      return ResponseEntity.status(404).body(new ErrorMessage("Id not found!"));
    }

    if (levelDTO.getLevel() > 3 || levelDTO.getLevel() < 1){
      return ResponseEntity.status(406).body(new ErrorMessage("Invalid soldier level!"));
    }

    if (MockData.gold.getAmount()< 10){
      return ResponseEntity.status(409).body(new ErrorMessage("Not enough resource!"));
    }

    MockData.soldierDTO.setLevel(levelDTO.getLevel());
    return ResponseEntity.status(200).body(MockData.soldierDTO);
  }

}