package com.greenfoxacademy.goddesstribesbackend.models.dtos;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition
public class LeaderboardBuildingsDTO {

  @ApiModelProperty(position = 1)
  private String KingdomName;
  @ApiModelProperty(position = 2)
  private int buildings;

  public LeaderboardBuildingsDTO() {
  }

  public LeaderboardBuildingsDTO(String kingdomName, int buildings) {
    KingdomName = kingdomName;
    this.buildings = buildings;
  }

  public String getKingdomName() {
    return KingdomName;
  }

  public void setKingdomName(String kingdomName) {
    KingdomName = kingdomName;
  }

  public int getBuildings() {
    return buildings;
  }

  public void setBuildings(int buildings) {
    this.buildings = buildings;
  }

}
