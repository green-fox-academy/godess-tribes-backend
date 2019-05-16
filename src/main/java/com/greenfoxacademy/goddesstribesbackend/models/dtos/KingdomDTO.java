package com.greenfoxacademy.goddesstribesbackend.models.dtos;

public class KingdomDTO {

  private Long id;
  private String kingdomName;
  private String username;

  public KingdomDTO() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
