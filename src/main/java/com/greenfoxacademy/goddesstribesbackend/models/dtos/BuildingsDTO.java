package com.greenfoxacademy.goddesstribesbackend.models.dtos;


import io.swagger.annotations.SwaggerDefinition;

import java.util.List;

@SwaggerDefinition
public class BuildingsDTO {

  private List<BuildingDTO> buildings;

  public BuildingsDTO() {
  }

  public BuildingsDTO(List<BuildingDTO> buildings) {
    this.buildings = buildings;
  }

  public void add(BuildingDTO buildingDTO) {
    buildings.add(buildingDTO);
  }

  public List<BuildingDTO> getBuildings() {
    return buildings;
  }

  public void setBuildings(List<BuildingDTO> buildings) {
    this.buildings = buildings;
  }

}
