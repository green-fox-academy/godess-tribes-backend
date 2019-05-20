package com.greenfoxacademy.goddesstribesbackend.models.dtos;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.SwaggerDefinition;

import java.time.LocalDateTime;

@SwaggerDefinition
public class SoldierDTO {

  @ApiModelProperty(position = 1)
  private Long id;
  @ApiModelProperty(position = 2)
  private int level;
  @ApiModelProperty(position = 3)
  private LocalDateTime startedAt;
  @ApiModelProperty(position = 4)
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
