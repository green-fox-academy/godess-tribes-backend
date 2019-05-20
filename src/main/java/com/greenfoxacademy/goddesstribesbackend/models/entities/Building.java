package com.greenfoxacademy.goddesstribesbackend.models.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public abstract class Building {

  public static final int MAX_LEVEL = 3;
  private static final int START_LEVEL = 1;
  private static final int START_UPGRADING_COST = 100;
  private static final int START_UPGRADING_TIME = 1;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private int level;
  private int upgradingCost;
  private int upgradingTime;
  private LocalDateTime startedAt;
  private LocalDateTime finishedAt;

  @ManyToOne(optional = false)
  private Kingdom kingdom;

  public Building() {
  }

  public Building(Kingdom kingdom) {
    this(kingdom, LocalDateTime.now());
  }

  public Building(Kingdom kingdom, LocalDateTime startedAt) {
    level = START_LEVEL;
    upgradingCost = START_UPGRADING_COST;
    upgradingTime = START_UPGRADING_TIME;
    this.startedAt = startedAt;
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

  public int getUpgradingCost() {
    return upgradingCost;
  }

  public void setUpgradingCost(int upgradingCost) {
    this.upgradingCost = upgradingCost;
  }

  public int getUpgradingTime() {
    return upgradingTime;
  }

  public void setUpgradingTime(int upgradingTime) {
    this.upgradingTime = upgradingTime;
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

}
