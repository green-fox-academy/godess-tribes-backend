package com.greenfoxacademy.goddesstribesbackend.controllers;

import com.greenfoxacademy.goddesstribesbackend.models.dtos.ResourcesDTO;
import com.greenfoxacademy.goddesstribesbackend.models.entities.Kingdom;
import com.greenfoxacademy.goddesstribesbackend.services.KingdomService;
import com.greenfoxacademy.goddesstribesbackend.services.ProductionService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

  private KingdomService kingdomService;
  private ProductionService productionService;

  @Autowired
  public ResourceController(KingdomService kingdomService, ProductionService productionService) {
    this.kingdomService = kingdomService;
    this.productionService = productionService;
  }

  @ApiImplicitParams({@ApiImplicitParam(name = "token", value = "Authorization token", required = true, dataType = "string", paramType = "header") })
  @ApiResponses(value = {@ApiResponse(code = 200, message ="OK", response = ResourcesDTO.class)})
  @GetMapping("/kingdom/resources")
  public ResponseEntity<Object> listResources() {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    Kingdom kingdom = kingdomService.findKingdomByUsername(username);
    return ResponseEntity.status(200).body(productionService.createResourcesDTO(kingdom.getId()));
  }

}
