package com.greenfoxacademy.goddesstribesbackend.modells.dtos;

public class UserAndKingdomRequestDTO {

  private String username;
  private String password;
  private String kingdomname;

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

  public String getKingdomname() {
    return kingdomname;
  }

  public void setKingdomname(String kingdomname) {
    this.kingdomname = kingdomname;
  }

}
