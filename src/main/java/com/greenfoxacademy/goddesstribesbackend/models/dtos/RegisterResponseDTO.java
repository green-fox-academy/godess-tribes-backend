package com.greenfoxacademy.goddesstribesbackend.models.dtos;

import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition
public class RegisterResponseDTO {

  private Long userId;
  private String username;
  private Long kingdomId;

  public RegisterResponseDTO(Long userId, String username, Long kingdomId) {
    this.userId = userId;
    this.username = username;
    this.kingdomId = kingdomId;
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

  public Long getKingdomId() {
    return kingdomId;
  }

  public void setKingdomId(Long kingdomId) {
    this.kingdomId = kingdomId;
  }

}
