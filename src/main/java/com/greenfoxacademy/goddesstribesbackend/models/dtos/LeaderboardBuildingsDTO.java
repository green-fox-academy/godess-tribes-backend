package com.greenfoxacademy.goddesstribesbackend.models.dtos;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition
public class LeaderboardBuildingsDTO {

  @ApiModelProperty(position = 1)
  private String kingdomName;
  @ApiModelProperty(position = 2)
  private int buildings;

  public LeaderboardBuildingsDTO() {
  }

  public LeaderboardBuildingsDTO(String kingdomName, int buildings) {
    this.kingdomName = kingdomName;
    this.buildings = buildings;
  }

  public String getKingdomName() {
    return kingdomName;
  }

  public void setKingdomName(String kingdomName) {
    this.kingdomName = kingdomName;
  }

  public int getBuildings() {
    return buildings;
  }

  public void setBuildings(int buildings) {
    this.buildings = buildings;
  }

}
