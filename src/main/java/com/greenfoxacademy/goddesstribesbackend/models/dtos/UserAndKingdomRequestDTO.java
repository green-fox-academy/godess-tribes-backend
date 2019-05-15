package com.greenfoxacademy.goddesstribesbackend.models.dtos;

public class UserAndKingdomRequestDTO {

  private String username;
  private String password;
  private String kingdomName;

  public UserAndKingdomRequestDTO() {
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getKingdomName() {
    return kingdomName;
  }

  public void setKingdomName(String kingdomName) {
    this.kingdomName = kingdomName;
  }

}
