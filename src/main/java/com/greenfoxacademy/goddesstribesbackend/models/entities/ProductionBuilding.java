package com.greenfoxacademy.goddesstribesbackend.models.entities;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public abstract class ProductionBuilding extends Building {

  private int productionRate;

  public ProductionBuilding() {
  }

  public ProductionBuilding(Kingdom kingdom, int productionRate) {
    this(kingdom, productionRate, LocalDateTime.now());
  }

  public ProductionBuilding(Kingdom kingdom, int productionRate, LocalDateTime startedAt) {
    super(kingdom, startedAt);
    this.productionRate = productionRate;
  }

  public int getProductionRate() {
    return productionRate;
  }

  public void setProductionRate(int productionRate) {
    this.productionRate = productionRate;
  }

}
