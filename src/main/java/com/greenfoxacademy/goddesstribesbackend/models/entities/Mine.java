package com.greenfoxacademy.goddesstribesbackend.models.entities;

import com.greenfoxacademy.goddesstribesbackend.models.BuildingTypeENUM;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class Mine extends ProductionBuilding {

  private static final int START_PRODUCTION_RATE = 10;

  public Mine() {
  }

  public Mine(Kingdom kingdom) {
    this(kingdom, LocalDateTime.now());
  }

  public Mine(Kingdom kingdom, LocalDateTime startedAt) {
    super(kingdom, START_PRODUCTION_RATE, startedAt);
    this.setBuildingType(BuildingTypeENUM.MINE);
  }

}
