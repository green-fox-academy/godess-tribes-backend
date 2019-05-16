package com.greenfoxacademy.goddesstribesbackend.controllers;

import com.greenfoxacademy.goddesstribesbackend.models.dtos.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class KingdomController {

  @GetMapping("/kingdom")
  public ResponseEntity<Object> findOwnKingdom() {
    KingdomDTO kingdomDTO = new KingdomDTO();
//    kingdomDTO.setId(213L);
//    kingdomDTO.setKingdomName("My Kingdom");
//    kingdomDTO.setUserId(12L);
//    BuildingDTO buildingDTO = new BuildingDTO();
//    buildingDTO.setId(123L);
//    buildingDTO.setBuldingTypeENUM(BuldingTypeENUM.BARRACK);
//    buildingDTO.setStartedAt(LocalDateTime.now());
//    buildingDTO.setFinishedAt(LocalDateTime.now());
//    BuildingsDTO buildingsDTO = new BuildingsDTO();
//    buildingsDTO.add(buildingDTO);
//    ResourceDTO resourceDTO = new ResourceDTO();
//    resourceDTO.setResourceTypeENUM(ResourceTypeENUM.FOOD);
//    resourceDTO.setAmount(223);
//    resourceDTO.setGenerationRate(2);
//    kingdomDTO.setResources(resourceDTO);
//    kingdomDTO.setUserId();



    return ResponseEntity.status(200).body(kingdomDTO);
  }

}
