package com.greenfoxacademy.goddesstribesbackend.modells.dtos;

public class UserAndKingdomResponseDTO {

  private Long id;
  private String username;
  private Long kingdomId;

  public UserAndKingdomResponseDTO() {
  }

  public UserAndKingdomResponseDTO(Long id, String username, Long kingdomId) {
    this.id = id;
    this.username = username;
    this.kingdomId = kingdomId;
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

}
