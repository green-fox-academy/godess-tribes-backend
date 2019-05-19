package com.greenfoxacademy.goddesstribesbackend.models.dtos;

public class RegisterResponseDTO {

  private Long userId;
  private String username;
  private String kingdomName;

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
