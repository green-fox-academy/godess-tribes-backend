package com.greenfoxacademy.goddesstribesbackend.models.entities;

import com.greenfoxacademy.goddesstribesbackend.models.BuildingTypeENUM;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class Farm extends ProductionBuilding {

  private static final int START_PRODUCTION_RATE = 10;

  public Farm() {
  }

  public Farm(Kingdom kingdom) {
    this(kingdom, LocalDateTime.now());
  }

  public Farm(Kingdom kingdom, LocalDateTime startedAt) {
    super(kingdom, START_PRODUCTION_RATE, startedAt);
    this.setType(BuildingTypeENUM.FARM);
  }

}
