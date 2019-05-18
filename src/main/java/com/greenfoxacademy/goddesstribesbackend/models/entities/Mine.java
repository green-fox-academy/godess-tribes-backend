package com.greenfoxacademy.goddesstribesbackend.models.entities;

import javax.persistence.Entity;

@Entity
public class Mine extends ProductionBuilding {

  private static int productionRate = 10;

  public Mine() {
  }

  public Mine(Kingdom kingdom) {
    super(productionRate, kingdom);
  }

}
