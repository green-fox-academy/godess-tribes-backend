package com.greenfoxacademy.goddesstribesbackend.models.dtos;

import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition
public class BuildingTypeDTO {

  private String type;

  public BuildingTypeDTO() {
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
