package com.greenfoxacademy.goddesstribesbackend.controllers;

import com.greenfoxacademy.goddesstribesbackend.models.dtos.*;
import com.greenfoxacademy.goddesstribesbackend.security.jwt.JWTUtility;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SoldierController {

  @GetMapping("/kingdom/soldiers")
  public ResponseEntity<Object> mockListOfSoldiers(@ResponseBody TokenDTO tokenDTO) {
    BuildingsDTO buildingsDTO = new BuildingsDTO();
    if (JWTUtility.parseToken(tokenDTO.getToken()) == null) {
      return ResponseEntity.status(200).body(new ErrorMessage("van ilyen?"))
    } return ResponseEntity.status(400).body(new SoldierDTO());
  }

//  @PostMapping ("/kingdom/soldiers")
// Itt mi történik? Nincs leírva az apiban

  @GetMapping("/kingdom/soldiers/{id}")
  public ResponseEntity<Object> mockRenderSoldier(@PathVariable Long id) {
    SoldierDTO soldierDTO = new SoldierDTO();
    if (soldierService.checkBuildingById(id)) {
      return ResponseEntity.status(200).body(new SoldierDTO());
    }
    return ResponseEntity.status(404).body("Id not found");
  }

  @PutMapping("/kingdom/soldiers/{id}")
  public ResponseEntity<Object> mockChangeSoldierLevel(@PathVariable Long id, @RequestBody SoldierDTO soldierDTO) {
    ResourceDTO resourceDTO = new ResourceDTO();
    int levelType = soldierDTO.getLevel();
//if token is valid and the user has enough resources, it returns a HTTP 200 status with the updated building
    if (!buildingService.checkSoldierById(id)){
      return ResponseEntity.status(404).body(new ErrorMessage("Id not found"));
    }
    if (levelType > 3 || levelType <1 ){
      return ResponseEntity.status(406).body(new ErrorMessage("Invalid building level"));
    }
    if (resourceDTO.getAmount()< 100){
      return ResponseEntity.status(409).body(new ErrorMessage("Not enough resource"));
    }
    return ResponseEntity.status(200).body(new SoldierDTO());
  }
}