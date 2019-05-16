package com.greenfoxacademy.goddesstribesbackend.models.dtos;

public class StatusOkMessage {

  private String status;
  private String message;

  public StatusOkMessage(String message) {
    status = "Ok";
    this.message = message;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
