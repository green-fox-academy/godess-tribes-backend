package com.greenfoxacademy.goddesstribesbackend.models.dtos;

import java.time.LocalDateTime;

public class BuildingDTO {
  private Long id;
  private BuldingTypeENUM buldingTypeENUM;
  private int level;
  private LocalDateTime startedAt;
  private LocalDateTime finishedAt;

  public BuildingDTO() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BuldingTypeENUM getBuldingTypeENUM() {
    return buldingTypeENUM;
  }

  public void setBuldingTypeENUM(BuldingTypeENUM buldingTypeENUM) {
    this.buldingTypeENUM = buldingTypeENUM;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
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
