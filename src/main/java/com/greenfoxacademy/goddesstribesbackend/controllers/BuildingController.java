package com.greenfoxacademy.goddesstribesbackend.controllers;

import com.greenfoxacademy.goddesstribesbackend.models.dtos.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BuildingController {

  @GetMapping("/kingdom/buildings")
  public ResponseEntity<Object> mockListOfBuildings(@RequestBody TokenDTO tokenDTO) {
    BuildingsDTO buildingsDTO = new BuildingsDTO();
    return ResponseEntity.status(400).body(buildingsDTO);
  }

  @PostMapping("/kingdom/buildings")
  public ResponseEntity<Object> mockCreateABuilding (@RequestBody BuildingDTO buildingDTO) {
    String buildingType = buildingDTO.getBuldingTypeENUM().toString();
    BuildingsDTO buildingsDTO = new BuildingsDTO();
    ResourceDTO resourceDTO = new ResourceDTO();

    if (buildingType == null || buildingType.isEmpty()) {
      return ResponseEntity.status(400).body(new ErrorMessage("Missing parameter(s): type!"));
    }
    if (buildingType != null && (buildingType != "farm" || buildingType != "mine" || buildingType != "barracks")) {
      return ResponseEntity.status(406).body(new ErrorMessage("Invalid building type"));
    }
    if ((buildingType == "farm" || buildingType == "mine" || buildingType == "barracks") && resourceDTO.getAmount() <= 100) {
      return ResponseEntity.status(409).body(new ErrorMessage("Not enough resource"));
    }
//    Model: farm, barracks, mine osztályok hiányoznak, save - service hiányzik hozzá! de egy üres BuildingDTO-t már át tudunk adni.
    return ResponseEntity.status(200).body(buildingDTO);
  }

  @GetMapping("/kingdom/buildings/{id}")
  public ResponseEntity<Object> mockRenderBuilding (@PathVariable Long id){
    BuildingsDTO buildingsDTO = new BuildingsDTO();
//    buildingService hiányzik - ezt még meg kell írni!
    if (buildingService.checkBuildingById(id){
      return ResponseEntity.status(200).body(new BuildingsDTO());
    }
    if (!buildingService.checkBuildingById(id)){
      return ResponseEntity.status(404).body(new ErrorMessage("Id not found"));
    }
  }

  @PutMapping("/kingdom/buildings/{id}")
  public ResponseEntity<Object>mockChangeBuildingLevel(@PathVariable Long id, @RequestBody BuildingDTO buildingDTO){
    ResourceDTO resourceDTO = new ResourceDTO();
    int levelType = buildingDTO.getLevel();
//    mi az, hogy a parameter missing? Itt most nulla, majd beszéljünk róla.
    if (levelType == 0 || id == null){
      return ResponseEntity.status(400).body(new ErrorMessage("Missing parameter(s): <type>!"));
    }
//    buildingService hiányzik
    if (!buildingService.checkBuildingById(id)){
      return ResponseEntity.status(404).body(new ErrorMessage("Id not found"));
    }
    if (levelType > 3 || levelType <1 ){
      return ResponseEntity.status(406).body(new ErrorMessage("Invalid building level"));
    }
    if (resourceDTO.getAmount()< 100){
      return ResponseEntity.status(409).body(new ErrorMessage("Not enough resource"));
    }
    return ResponseEntity.status(200).body(new BuildingsDTO());
  }






}
