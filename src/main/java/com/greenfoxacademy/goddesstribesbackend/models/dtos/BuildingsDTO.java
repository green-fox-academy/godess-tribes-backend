package com.greenfoxacademy.goddesstribesbackend.models.dtos;


import java.util.List;

public class BuildingsDTO {
  private List<BuildingDTO> buildingDTOS;

  public BuildingsDTO() {
  }


  public List<BuildingDTO> getBuildingDTOS() {
    return buildingDTOS;
  }

  public void setBuildingDTOS(List<BuildingDTO> buildingDTOS) {
    this.buildingDTOS = buildingDTOS;
  }
}
