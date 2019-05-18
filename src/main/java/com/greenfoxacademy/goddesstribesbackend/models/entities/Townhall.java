package com.greenfoxacademy.goddesstribesbackend.models.entities;

import javax.persistence.Entity;

@Entity
public class Townhall extends Building {

  private int foodCapacity;
  private int goldCapacity;

  public Townhall() {
  }

  public Townhall(Kingdom kingdom) {
    super(kingdom);
    foodCapacity = 1000;
    goldCapacity = 1000;
  }

  public int getFoodCapacity() {
    return foodCapacity;
  }

  public void setFoodCapacity(int foodCapacity) {
    this.foodCapacity = foodCapacity;
  }

  public int getGoldCapacity() {
    return goldCapacity;
  }

  public void setGoldCapacity(int goldCapacity) {
    this.goldCapacity = goldCapacity;
  }

}
