package com.greenfoxacademy.goddesstribesbackend.models.dtos;

import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition
public class LoginRequestDTO {

  private String username;
  private String password;

  public LoginRequestDTO() {
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
