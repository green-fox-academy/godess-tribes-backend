package com.greenfoxacademy.goddesstribesbackend.models.entities;

import com.greenfoxacademy.goddesstribesbackend.models.BuildingTypeENUM;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Townhall extends Building {

  public static int START_FOOD_AMOUNT = 50;
  public static int START_GOLD_AMOUNT = 100;
  private static final int START_FOOD_CAPACITY = 1000;
  private static final int START_GOLD_CAPACITY = 1000;

  private int foodCapacity;
  private int goldCapacity;
  @OneToMany(mappedBy = "townhall")
  private List<Resource> resources;

  public Townhall() {
  }

  public Townhall(Kingdom kingdom) {
    this(kingdom, LocalDateTime.now());
  }

  public Townhall(Kingdom kingdom, LocalDateTime startedAt) {
    super(kingdom, startedAt);
    foodCapacity = START_FOOD_CAPACITY;
    goldCapacity = START_GOLD_CAPACITY;
    this.setBuildingType(BuildingTypeENUM.TOWNHALL);
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
