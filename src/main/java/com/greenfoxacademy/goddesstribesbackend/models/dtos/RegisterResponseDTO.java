package com.greenfoxacademy.goddesstribesbackend.models.dtos;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition
public class RegisterResponseDTO {

  @ApiModelProperty(position = 1)
  private Long userId;
  @ApiModelProperty(position = 2)
  private String username;
  @ApiModelProperty(position = 3)
  private String kingdomName;

  public RegisterResponseDTO() {
  }

  public RegisterResponseDTO(Long userId, String username, String kingdomName) {
    this.userId = userId;
    this.username = username;
    this.kingdomName = kingdomName;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getKingdomName() {
    return kingdomName;
  }

  public void setKingdomName(String kingdomName) {
    this.kingdomName = kingdomName;
  }

}
