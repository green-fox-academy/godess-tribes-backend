package com.greenfoxacademy.goddesstribesbackend.models.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Soldier {

  private static final int START_LEVEL = 1;
  private static final int START_CONSUMPTION_RATE = 1;
  public static final int CREATION_COST = 10;
  public static final int UPGRADING_COST = 5;
  public static final int NEEDED_TIME = 1;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private int level;
  private int consumptionRate;
  private LocalDateTime startedAt;
  private LocalDateTime finishedAt;

  @ManyToOne(optional = false)
  private Barrack barrack;

  public Soldier() {
  }

  public Soldier(Barrack barrack) {
    level = START_LEVEL;
    consumptionRate = START_CONSUMPTION_RATE;
    startedAt = LocalDateTime.now();
    finishedAt = startedAt.plusMinutes(NEEDED_TIME);
    this.barrack = barrack;
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

  public int getConsumptionRate() {
    return consumptionRate;
  }

  public void setConsumptionRate(int consumptionRate) {
    this.consumptionRate = consumptionRate;
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

  public Barrack getBarrack() {
    return barrack;
  }

  public void setBarrack(Barrack barrack) {
    this.barrack = barrack;
  }

}
