package com.greenfoxacademy.goddesstribesbackend.models.entities;

import javax.persistence.Entity;

@Entity
public abstract class ProductionBuilding extends ActiveBuilding {

  private int productionRate;

  public ProductionBuilding() {
  }

  public ProductionBuilding(int productionRate, Kingdom kingdom) {
    super(kingdom);
    this.productionRate = productionRate;
  }

  public int getProductionRate() {
    return productionRate;
  }

  public void setProductionRate(int productionRate) {
    this.productionRate = productionRate;
  }

}
