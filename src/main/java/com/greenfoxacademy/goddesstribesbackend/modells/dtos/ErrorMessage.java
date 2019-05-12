package com.greenfoxacademy.goddesstribesbackend.modells.dtos;

public class ErrorMessage {

  private String status;
  private String message;

  public ErrorMessage(String message) {
    status = "error";
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
