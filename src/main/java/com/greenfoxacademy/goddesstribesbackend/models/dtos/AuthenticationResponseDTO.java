package com.greenfoxacademy.goddesstribesbackend.models.dtos;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition
public class AuthenticationResponseDTO {

  @ApiModelProperty(position = 1)
  private Long userId;
  @ApiModelProperty(position = 2)
  private Long kingdomId;

  public AuthenticationResponseDTO(Long userId, Long kingdomId) {
    this.userId = userId;
    this.kingdomId = kingdomId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getKingdomId() {
    return kingdomId;
  }

  public void setKingdomId(Long kingdomId) {
    this.kingdomId = kingdomId;
  }

}
