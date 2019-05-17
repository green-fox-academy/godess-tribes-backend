package com.greenfoxacademy.goddesstribesbackend.controllers;

import com.greenfoxacademy.goddesstribesbackend.models.MockData;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BuildingController {

  @GetMapping("/kingdom/buildings")
  public ResponseEntity<Object> mockListOfBuildings() {
    return ResponseEntity.status(200).body(MockData.buildingsDTO);
  }

  @PostMapping("/kingdom/buildings")
  public ResponseEntity<Object> mockCreateABuilding (@RequestBody BuildingTypeDTO buildingTypeDTO) {
    if (buildingTypeDTO.getType().isEmpty()) {
      return ResponseEntity.status(400).body(new ErrorMessage("Missing parameter(s): type!"));
    }
    if (buildingTypeDTO.getType() != BuldingTypeENUM.values().toString()) {
      return ResponseEntity.status(406).body(new ErrorMessage("Invalid building type"));
    }
    if (MockData.gold.getAmount() <= 250) {
      return ResponseEntity.status(409).body(new ErrorMessage("Not enough resource"));
    }
    return ResponseEntity.status(200).body(MockData.buildingDTO);
  }

  @GetMapping("/kingdom/buildings/{id}")
  public ResponseEntity<Object> mockRenderBuilding (@PathVariable Long id){
    if (MockData.buildingDTO.getId() == id){
      return ResponseEntity.status(200).body(MockData.buildingDTO);
    }
    return ResponseEntity.status(404).body(new ErrorMessage("Id not found"));
  }

  @PutMapping("/kingdom/buildings/{id}")
  public ResponseEntity<Object>mockChangeBuildingLevel(
      @PathVariable Long id,
      @RequestBody (required =  false) LevelDTO levelDTO){

    if (levelDTO.getLevel() == null){
      return ResponseEntity.status(400).body(new ErrorMessage("Missing parameter(s): <level>!"));
    }

    if (MockData.buildingDTO.getId() != id){
      return ResponseEntity.status(404).body(new ErrorMessage("Id not found!"));
    }

    if (levelDTO.getLevel() > 3 || levelDTO.getLevel() < 1){
      return ResponseEntity.status(406).body(new ErrorMessage("Invalid building level!"));
    }

    if (MockData.gold.getAmount()< 250){
      return ResponseEntity.status(409).body(new ErrorMessage("Not enough resource!"));
    }
    MockData.buildingDTO.setLevel(levelDTO.getLevel());
    return ResponseEntity.status(200).body(MockData.buildingDTO);
  }

}
