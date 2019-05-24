package com.greenfoxacademy.goddesstribesbackend.models.dtos;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition
public class LeaderboardSoldiersDTO {

  @ApiModelProperty(position = 1)
  private String kingdomName;
  @ApiModelProperty(position = 2)
  private int soldiers;

  public LeaderboardSoldiersDTO() {
  }

  public LeaderboardSoldiersDTO(String kingdomName, int soldiers) {
    this.kingdomName = kingdomName;
    this.soldiers = soldiers;
  }

  public String getKingdomName() {
    return kingdomName;
  }

  public void setKingdomName(String kingdomName) {
    this.kingdomName = kingdomName;
  }

  public int getSoldiers() {
    return soldiers;
  }

  public void setSoldiers(int soldiers) {
    this.soldiers = soldiers;
  }
}
