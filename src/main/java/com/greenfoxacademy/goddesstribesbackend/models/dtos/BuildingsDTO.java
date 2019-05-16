package com.greenfoxacademy.goddesstribesbackend.models.dtos;


import java.util.List;

public class BuildingsDTO {
  private List<BuildingDTO> buildingDTOS;

  public BuildingsDTO() {
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
