package com.greenfoxacademy.goddesstribesbackend.models.dtos;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition
public class UserDTO {

  @ApiModelProperty(position = 1)
  private Long id;
  @ApiModelProperty(position = 2)
  private String username;
  @ApiModelProperty(position = 3)
  private Long kingdomId;
  @ApiModelProperty(position = 4)
  private int points;

  public UserDTO() {
  }

  public UserDTO(Long id, String username, Long kingdomId, int points) {
    this.id = id;
    this.username = username;
    this.kingdomId = kingdomId;
    this.points = points;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Long getKingdomId() {
    return kingdomId;
  }

  public void setKingdomId(Long kingdomId) {
    this.kingdomId = kingdomId;
  }

  public int getPoints() {
    return points;
  }

  public void setPoints(int points) {
    this.points = points;
  }
}
