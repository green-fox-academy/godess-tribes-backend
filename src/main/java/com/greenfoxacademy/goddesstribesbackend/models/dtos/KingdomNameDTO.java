package com.greenfoxacademy.goddesstribesbackend.models.dtos;

import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition
public class KingdomNameDTO {

  private String name;

  public KingdomNameDTO() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
