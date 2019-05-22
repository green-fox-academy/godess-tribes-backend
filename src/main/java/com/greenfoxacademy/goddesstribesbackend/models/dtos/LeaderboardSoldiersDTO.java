package com.greenfoxacademy.goddesstribesbackend.models.dtos;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition
public class LeaderboardSoldiersDTO {

  @ApiModelProperty(position = 1)
  private String KingdomName;
  @ApiModelProperty(position = 2)
  private int soldiers;

  public LeaderboardSoldiersDTO() {
  }

  public LeaderboardSoldiersDTO(String kingdomName, int soldiers) {
    KingdomName = kingdomName;
    this.soldiers = soldiers;
  }

  public String getKingdomName() {
    return KingdomName;
  }

  public void setKingdomName(String kingdomName) {
    KingdomName = kingdomName;
  }

  public int getSoldiers() {
    return soldiers;
  }

  public void setSoldiers(int soldiers) {
    this.soldiers = soldiers;
  }
}
