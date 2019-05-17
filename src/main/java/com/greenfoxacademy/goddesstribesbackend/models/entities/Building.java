package com.greenfoxacademy.goddesstribesbackend.models.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public abstract class Building {

  private static int maxLevel = 3;
  private static int upgradingTime = 1;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private int level;

  @ManyToOne(optional = false)
  private Kingdom kingdom;

  private int upgradingCost;
  private LocalDateTime startedAt;
  private LocalDateTime finishedAt;

  public Building() {
  }

  public Building(Kingdom kingdom) {
    this.kingdom = kingdom;
    level = 1;
    upgradingCost = 100 * level;
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

  public Kingdom getKingdom() {
    return kingdom;
  }

  public void setKingdom(Kingdom kingdom) {
    this.kingdom = kingdom;
  }

  public int getUpgradingCost() {
    return upgradingCost;
  }

  public void setUpgradingCost(int upgradingCost) {
    this.upgradingCost = upgradingCost;
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

}
