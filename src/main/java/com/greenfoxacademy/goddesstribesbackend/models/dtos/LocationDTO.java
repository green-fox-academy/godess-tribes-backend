package com.greenfoxacademy.goddesstribesbackend.models.dtos;

import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition
public class LocationDTO {
  private int xCoord;
  private int yCoord;

  public LocationDTO() {
  }

  public LocationDTO(int xCoord, int yCoord) {
    this.xCoord = xCoord;
    this.yCoord = yCoord;
  }

  public int getxCoord() {
    return xCoord;
  }

  public void setxCoord(int xCoord) {
    this.xCoord = xCoord;
  }

  public int getyCoord() {
    return yCoord;
  }

  public void setyCoord(int yCoord) {
    this.yCoord = yCoord;
  }
}
