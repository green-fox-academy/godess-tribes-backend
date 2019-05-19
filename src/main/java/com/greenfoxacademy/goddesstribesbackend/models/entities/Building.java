package com.greenfoxacademy.goddesstribesbackend.models.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public abstract class Building {

  public static final int MAXLEVEL = 3;

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
    level = 1;
    upgradingCost = 100 * level;
    upgradingTime = 1;
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
