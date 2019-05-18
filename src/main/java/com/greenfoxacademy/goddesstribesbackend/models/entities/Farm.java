package com.greenfoxacademy.goddesstribesbackend.models.entities;

import javax.persistence.Entity;

@Entity
public class Farm extends ProductionBuilding {

  private static int productionRate = 10;

  public Farm() {
  }

  public Farm(Kingdom kingdom) {
    super(productionRate, kingdom);
  }

}
