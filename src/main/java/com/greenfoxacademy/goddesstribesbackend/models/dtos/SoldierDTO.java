package com.greenfoxacademy.goddesstribesbackend.models.dtos;

import java.time.LocalDateTime;

public class SoldierDTO {
    private Long id;
    private int level;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;

  public SoldierDTO() {
  }

  public SoldierDTO(Long id, int level, LocalDateTime startedAt, LocalDateTime finishedAt) {
    this.id = id;
    this.level = level;
    this.startedAt = startedAt;
    this.finishedAt = finishedAt;
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
