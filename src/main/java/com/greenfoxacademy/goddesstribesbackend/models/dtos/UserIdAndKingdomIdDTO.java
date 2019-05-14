package com.greenfoxacademy.goddesstribesbackend.models.dtos;

public class UserIdAndKingdomIdDTO {

  private Long userId;
  private Long kingdomId;

  public UserIdAndKingdomIdDTO(Long userId, Long kingdomId) {
    this.userId = userId;
    this.kingdomId = kingdomId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getKingdomId() {
    return kingdomId;
  }

  public void setKingdomId(Long kingdomId) {
    this.kingdomId = kingdomId;
  }

}
