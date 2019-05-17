package com.greenfoxacademy.goddesstribesbackend.controllers;

import com.greenfoxacademy.goddesstribesbackend.models.MockData;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.ErrorMessage;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.LevelDTO;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.SoldierDTO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class SoldierController {

  @ApiImplicitParams({
      @ApiImplicitParam(name = "token", value = "Authorization token",
          required = true, dataType = "string", paramType = "header") })
  @ApiResponses(value = {
      @ApiResponse(code = 200, message ="OK", response = SoldierDTO.class)})
  @GetMapping("/kingdom/soldiers")
  public ResponseEntity<Object> mockListOfSoldiers() {
    return ResponseEntity.status(200).body(MockData.soldiersDTO);
  }

  @ApiImplicitParams({
      @ApiImplicitParam(name = "token", value = "Authorization token",
          required = true, dataType = "string", paramType = "header") })
  @ApiResponses(value = {
      @ApiResponse(code = 200, message ="OK", response = SoldierDTO.class),
      @ApiResponse(code = 409, message ="Not enough resource")})
  @PostMapping("/kingdom/soldiers")
  public ResponseEntity<Object> mockTrainANewSoldier () {

    if (MockData.gold.getAmount() <= 10) {
      return ResponseEntity.status(409).body(new ErrorMessage("Not enough resource"));
    }
    return ResponseEntity.status(200).body(MockData.soldierDTO);

  }

  @ApiImplicitParams({
      @ApiImplicitParam(name = "token", value = "Authorization token",
          required = true, dataType = "string", paramType = "header") })
  @ApiResponses(value = {
      @ApiResponse(code = 200, message ="OK", response = SoldierDTO.class),
      @ApiResponse(code = 404, message ="Id not found")})
  @GetMapping("/kingdom/soldiers/{id}")
  public ResponseEntity<Object> mockRenderSoldier(@PathVariable Long id) {

    if (MockData.soldierDTO.getId() == id) {
      return ResponseEntity.status(200).body(MockData.soldierDTO);
    }
    return ResponseEntity.status(404).body(new ErrorMessage("Id not found"));
  }

  @ApiImplicitParams({
      @ApiImplicitParam(name = "token", value = "Authorization token",
          required = true, dataType = "string", paramType = "header") })
  @ApiResponses(value = {
      @ApiResponse(code = 200, message ="OK", response = SoldierDTO.class),
      @ApiResponse(code = 400, message ="Missing parameter(s): level!"),
      @ApiResponse(code = 404, message ="Id not found"),
      @ApiResponse(code = 406, message ="Invalid soldier level"),
      @ApiResponse(code = 409, message ="Not enough resource")})
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