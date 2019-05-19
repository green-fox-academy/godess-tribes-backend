package com.greenfoxacademy.goddesstribesbackend.controllers;

import com.greenfoxacademy.goddesstribesbackend.models.MockData;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.*;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BuildingController {

  @ApiImplicitParams({
      @ApiImplicitParam(name = "token", value = "Authorization token",
          required = true, dataType = "string", paramType = "header") })
  @ApiResponses(value = {
      @ApiResponse(code = 200, message ="OK", response = BuildingDTO.class)})
  @GetMapping(value = "/kingdom/buildings",
  produces = { "application/json" },
  consumes = { "application/json" })
  public ResponseEntity<Object> mockListOfBuildings() {
    return ResponseEntity.status(200).body(MockData.buildingsDTO);
  }

  @ApiImplicitParams({
      @ApiImplicitParam(name = "token", value = "Authorization token",
          required = true, dataType = "string", paramType = "header") })
  @ApiResponses(value = {
      @ApiResponse(code = 200, message ="OK", response = BuildingDTO.class),
      @ApiResponse(code = 400, message ="Missing parameter(s): type!", response = ErrorMessage.class),
      @ApiResponse(code = 406, message ="Invalid building type", response = ErrorMessage.class),
      @ApiResponse(code = 409, message ="Not enough resource", response = ErrorMessage.class)})
  @PostMapping(value = "/kingdom/buildings",
  produces = { "application/json" },
  consumes = { "application/json" })
  public ResponseEntity<Object> mockCreateABuilding (@RequestBody (required = false) BuildingTypeDTO buildingTypeDTO) {
    if (buildingTypeDTO.getType().isEmpty() || buildingTypeDTO.getType() == null) {
      return ResponseEntity.status(400).body(new ErrorMessage("Missing parameter(s): type!"));
    }
    if (!(BuldingTypeENUM.FARM.toString().equalsIgnoreCase(buildingTypeDTO.getType())) &&
        !(BuldingTypeENUM.MINE.toString().equalsIgnoreCase(buildingTypeDTO.getType())) &&
        !(BuldingTypeENUM.BARRACK.toString().equalsIgnoreCase(buildingTypeDTO.getType())) )
        {
      return ResponseEntity.status(406).body(new ErrorMessage("Invalid building type"));
    }
    if (MockData.gold.getAmount() < 250) {
      return ResponseEntity.status(409).body(new ErrorMessage("Not enough resource"));
    }
    if (BuldingTypeENUM.FARM.toString().equalsIgnoreCase(buildingTypeDTO.getType())){
      MockData.gold.setAmount(MockData.gold.getAmount() - 250);
      return ResponseEntity.status(200).body(MockData.farm);
    }
    if (BuldingTypeENUM.MINE.toString().equalsIgnoreCase(buildingTypeDTO.getType())){
      MockData.gold.setAmount(MockData.gold.getAmount() - 250);
      return ResponseEntity.status(200).body(MockData.mine);
    }
    MockData.gold.setAmount(MockData.gold.getAmount() - 250);
    return ResponseEntity.status(200).body(MockData.barrack);
  }

  @ApiImplicitParams({
      @ApiImplicitParam(name = "token", value = "Authorization token",
          required = true, dataType = "string", paramType = "header") })
  @ApiResponses(value = {
      @ApiResponse(code = 200, message ="OK", response = BuildingDTO.class),
      @ApiResponse(code = 409, message ="Id not found", response = ErrorMessage.class)})
  @GetMapping(value = "/kingdom/buildings/{id}",
  produces = { "application/json" },
  consumes = { "application/json" })
  public ResponseEntity<Object> mockRenderBuilding (@PathVariable Long id){
    if (MockData.farm.getId() == id){
      return ResponseEntity.status(200).body(MockData.farm);
    }
    return ResponseEntity.status(404).body(new ErrorMessage("Id not found"));
  }

  @ApiImplicitParams({
      @ApiImplicitParam(name = "token", value = "Authorization token",
          required = true, dataType = "string", paramType = "header") })
  @ApiResponses(value = {
      @ApiResponse(code = 200, message ="OK", response = BuildingDTO.class),
      @ApiResponse(code = 400, message ="Missing parameter(s): level!", response = ErrorMessage.class),
      @ApiResponse(code = 404, message ="Id not found", response = ErrorMessage.class),
      @ApiResponse(code = 406, message ="Invalid building level", response = ErrorMessage.class),
      @ApiResponse(code = 409, message ="Not enough resource", response = ErrorMessage.class)})
  @PutMapping(value = "/kingdom/buildings/{id}",
  produces = { "application/json" },
  consumes = { "application/json" })
  public ResponseEntity<Object>mockChangeBuildingLevel(
      @PathVariable Long id,
      @RequestBody (required =  false) LevelDTO levelDTO){

    if (levelDTO.getLevel() == null){
      return ResponseEntity.status(400).body(new ErrorMessage("Missing parameter(s): <level>!"));
    }

    if (MockData.farm.getId() != id){
      return ResponseEntity.status(404).body(new ErrorMessage("Id not found!"));
    }

    if (levelDTO.getLevel() > 3 || levelDTO.getLevel() < 1){
      return ResponseEntity.status(406).body(new ErrorMessage("Invalid building level!"));
    }

    if (MockData.gold.getAmount()< 250){
      return ResponseEntity.status(409).body(new ErrorMessage("Not enough resource!"));
    }
    MockData.farm.setLevel(levelDTO.getLevel());
    return ResponseEntity.status(200).body(MockData.farm);
  }

}
