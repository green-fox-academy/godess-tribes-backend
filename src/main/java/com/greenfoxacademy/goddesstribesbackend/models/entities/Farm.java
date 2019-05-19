package com.greenfoxacademy.goddesstribesbackend.models.entities;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class Farm extends ProductionBuilding {

  private static int productionRate = 10;

  public Farm() {
  }

  public Farm(Kingdom kingdom) {
    super(kingdom, productionRate);
  }

  public Farm(Kingdom kingdom, LocalDateTime startedAt) {
    super(kingdom, productionRate, startedAt);
  }

}
