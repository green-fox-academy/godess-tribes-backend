package com.greenfoxacademy.goddesstribesbackend.models.dtos;


import io.swagger.annotations.SwaggerDefinition;

import java.util.List;

@SwaggerDefinition
public class BuildingsDTO {
  private List<BuildingDTO> buildingDTOS;

  public BuildingsDTO() {
  }

  public BuildingsDTO(List<BuildingDTO> buildingDTOS) {
    this.buildingDTOS = buildingDTOS;
  }

  public void add(BuildingDTO buildingDTO){
    buildingDTOS.add(buildingDTO);
  }

  public List<BuildingDTO> getBuildingDTOS() {
    return buildingDTOS;
  }

  public void setBuildingDTOS(List<BuildingDTO> buildingDTOS) {
    this.buildingDTOS = buildingDTOS;
  }
}
