package com.greenfoxacademy.goddesstribesbackend.models.dtos;

public class TokenMessage {

  private String status;
  private String token;

  public TokenMessage(String token) {
    status = "ok";
    this.token = token;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

}
