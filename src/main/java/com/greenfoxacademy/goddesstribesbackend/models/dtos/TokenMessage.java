package com.greenfoxacademy.goddesstribesbackend.models.dtos;

public class TokenMessage {

  private String status;
  private String tribes_token;

  public TokenMessage(String token) {
    status = "ok";
    this.tribes_token = token;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getTribes_token() {
    return tribes_token;
  }

  public void setTribes_token(String tribes_token) {
    this.tribes_token = tribes_token;
  }

}
