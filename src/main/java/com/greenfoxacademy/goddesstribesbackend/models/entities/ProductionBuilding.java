package com.greenfoxacademy.goddesstribesbackend.models.entities;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public abstract class ProductionBuilding extends ActiveBuilding {

  private int productionRate;

  public ProductionBuilding() {
  }

  public ProductionBuilding(Kingdom kingdom, int productionRate) {
    super(kingdom);
    this.productionRate = productionRate;
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
