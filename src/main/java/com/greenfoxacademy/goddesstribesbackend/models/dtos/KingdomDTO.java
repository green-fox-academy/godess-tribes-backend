package com.greenfoxacademy.goddesstribesbackend.models.dtos;

public class KingdomDTO {

  private Long id;
  private String kingdomname;
  private String username;

  public KingdomDTO() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
