package com.greenfoxacademy.goddesstribesbackend.models.dtos;

public class KingdomDTO {

  private Long kingdomId;
  private String kingdomName;
  private String username;

  public KingdomDTO() {
  }

  public Long getKingdomId() {
    return kingdomId;
  }

  public void setKingdomId(Long kingdomId) {
    this.kingdomId = kingdomId;
  }

  public String getKingdomName() {
    return kingdomName;
  }

  public void setKingdomName(String kingdomName) {
    this.kingdomName = kingdomName;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

}
