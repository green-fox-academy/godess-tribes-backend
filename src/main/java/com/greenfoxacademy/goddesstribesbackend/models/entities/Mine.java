package com.greenfoxacademy.goddesstribesbackend.models.entities;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class Mine extends ProductionBuilding {

  private static int productionRate = 10;

  public Mine() {
  }

  public Mine(Kingdom kingdom) {
    super(kingdom, productionRate);
  }

  public Mine(Kingdom kingdom, LocalDateTime startedAt) {
    super(kingdom, productionRate, startedAt);
  }

}
