package com.greenfoxacademy.goddesstribesbackend.models.entities;

import com.greenfoxacademy.goddesstribesbackend.models.BuildingTypeENUM;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public abstract class Building {

  public static final int MAX_LEVEL = 3;
  private static final int START_LEVEL = 1;
  public static final int CREATION_COST = 250;
  public static final int CREATION_TIME = 2;
  private static final int START_UPGRADE_COST = 100;
  private static final int START_UPGRADE_TIME = 1;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private int level;
  private int upgradeCost;
  private int upgradeTime;
  private LocalDateTime startedAt;
  private LocalDateTime finishedAt;

  @ManyToOne(optional = false)
  @JoinColumn(name = "kingdomId")
  private Kingdom kingdom;

  private BuildingTypeENUM buildingType;

  public Building() {
  }

  public Building(Kingdom kingdom) {
    this(kingdom, LocalDateTime.now());
  }

  public Building(Kingdom kingdom, LocalDateTime startedAt) {
    level = START_LEVEL;
    upgradeCost = START_UPGRADE_COST;
    upgradeTime = START_UPGRADE_TIME;
    this.startedAt = startedAt;
    finishedAt = startedAt.plusMinutes(CREATION_TIME);
    this.kingdom = kingdom;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public int getUpgradeCost() {
    return upgradeCost;
  }

  public void setUpgradeCost(int upgradeCost) {
    this.upgradeCost = upgradeCost;
  }

  public int getUpgradeTime() {
    return upgradeTime;
  }

  public void setUpgradeTime(int upgradeTime) {
    this.upgradeTime = upgradeTime;
  }

  public LocalDateTime getStartedAt() {
    return startedAt;
  }

  public void setStartedAt(LocalDateTime startedAt) {
    this.startedAt = startedAt;
  }

  public LocalDateTime getFinishedAt() {
    return finishedAt;
  }

  public void setFinishedAt(LocalDateTime finishedAt) {
    this.finishedAt = finishedAt;
  }

  public Kingdom getKingdom() {
    return kingdom;
  }

  public void setKingdom(Kingdom kingdom) {
    this.kingdom = kingdom;
  }

  public BuildingTypeENUM getBuildingType() {
    return buildingType;
  }

  public void setBuildingType(BuildingTypeENUM buildingType) {
    this.buildingType = buildingType;
  }

}
