package com.greenfoxacademy.goddesstribesbackend.models.dtos;

public class KingdomDTO {

  private Long kingdomId;
  private String kingdomname;
  private String username;

  public KingdomDTO() {
  }

  public Long getKingdomId() {
    return kingdomId;
  }

  public void setKingdomId(Long kingdomId) {
    this.kingdomId = kingdomId;
  }

  public String getKingdomname() {
    return kingdomname;
  }

  public void setKingdomname(String kingdomname) {
    this.kingdomname = kingdomname;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

}
