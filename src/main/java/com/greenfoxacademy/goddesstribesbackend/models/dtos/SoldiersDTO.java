package com.greenfoxacademy.goddesstribesbackend.models.dtos;

import io.swagger.annotations.SwaggerDefinition;

import java.util.List;

@SwaggerDefinition
public class SoldiersDTO {

  private List<SoldierDTO> soldiers;

  public SoldiersDTO() {
  }

  public SoldiersDTO(List<SoldierDTO> soldiers) {
    this.soldiers = soldiers;
  }

  public List<SoldierDTO> getSoldiers() {
    return soldiers;
  }

  public void setSoldiers(List<SoldierDTO> soldiers) {
    this.soldiers = soldiers;
  }

}
