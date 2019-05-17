package com.greenfoxacademy.goddesstribesbackend.models.entities;

import javax.persistence.Entity;

@Entity
public abstract class ActiveBuilding extends Building {

  private static int creatingCost = 250;
  private static int creatingTime = 2;

  public ActiveBuilding() {
  }

  public ActiveBuilding(Kingdom kingdom) {
    super(kingdom);
  }

}
