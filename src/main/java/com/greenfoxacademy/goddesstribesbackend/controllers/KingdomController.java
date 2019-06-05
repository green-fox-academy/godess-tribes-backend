package com.greenfoxacademy.goddesstribesbackend.controllers;

import com.greenfoxacademy.goddesstribesbackend.models.MockData;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.ErrorMessage;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.KingdomDTO;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.KingdomNameDTO;
import com.greenfoxacademy.goddesstribesbackend.models.entities.Kingdom;
import com.greenfoxacademy.goddesstribesbackend.services.KingdomService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class KingdomController {

  private KingdomService kingdomService;

  @Autowired
  public KingdomController(KingdomService kingdomService) {
    this.kingdomService = kingdomService;
  }


  @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "Authorization token",required = true, dataType = "string", paramType = "header")})
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = KingdomDTO.class)})
  @GetMapping("/kingdom")
  public ResponseEntity<Object> findOwnKingdom() {
    return ResponseEntity.status(200).body(MockData.kingdomDTO);
  }

  @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = KingdomDTO.class), @ApiResponse(code = 400, message = "Missing parameter(s): name!", response = ErrorMessage.class)})
  @PutMapping("/kingdom")
  public ResponseEntity<Object> changeKingdomName(@RequestBody KingdomNameDTO kingdomNameDTO) {

    if (kingdomNameDTO.getName() == null || kingdomNameDTO.getName().isEmpty()) {
      return ResponseEntity.status(400).body(new ErrorMessage("Missing parameter(s): <name>!"));
    }

    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    Kingdom kingdom = kingdomService.findKingdomByUsername(username);
    Kingdom renamedKingdom = kingdomService.renameKingdom(kingdomNameDTO.getName(), kingdom);
    return ResponseEntity.status(200).body(kingdomService.createKingdomDTO(renamedKingdom));
  }

  @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "string", paramType = "header")})
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = KingdomDTO.class), @ApiResponse(code = 404, message = "Id not found", response = ErrorMessage.class)})
  @GetMapping("/kingdom/{id}")
  public ResponseEntity<Object> mockRenderKingdom(@PathVariable Long id) {

    if (MockData.kingdomDTO.getId() == id) {
      return ResponseEntity.status(200).body(MockData.kingdomDTO);
    }

    return ResponseEntity.status(404).body(new ErrorMessage("Id not found"));
  }

}
