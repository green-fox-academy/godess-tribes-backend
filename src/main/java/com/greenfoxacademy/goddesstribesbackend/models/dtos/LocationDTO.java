package com.greenfoxacademy.goddesstribesbackend.models.dtos;

public class LocationDTO {
  private int xCoord;
  private int yCoord;

  public LocationDTO() {
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
