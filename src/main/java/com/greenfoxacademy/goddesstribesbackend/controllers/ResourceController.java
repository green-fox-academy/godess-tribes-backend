package com.greenfoxacademy.goddesstribesbackend.controllers;


import com.greenfoxacademy.goddesstribesbackend.models.MockData;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.ResourcesDTO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

  @ApiImplicitParams({
      @ApiImplicitParam(name = "token", value = "Authorization token",
          required = true, dataType = "string", paramType = "header") })
  @ApiResponses(value = {
      @ApiResponse(code = 200, message ="OK", response = ResourcesDTO.class)})
  @GetMapping("/kingdom/resources")
  public ResponseEntity<Object> mockListResources() {
    return ResponseEntity.status(200).body(MockData.resourcesDTO);
  }

}
