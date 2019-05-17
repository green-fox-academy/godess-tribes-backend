package com.greenfoxacademy.goddesstribesbackend.models.dtos;

public class UserDTO {

  private Long id;
  private String username;
  private Long kingdomId;
  private int points;

  public UserDTO() {
  }

  public UserDTO(Long id, String username, Long kingdomId, int points) {
    this.id = id;
    this.username = username;
    this.kingdomId = kingdomId;
    this.points = points;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public int getPoints() {
    return points;
  }

  public void setPoints(int points) {
    this.points = points;
  }
}
