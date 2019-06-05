package com.greenfoxacademy.goddesstribesbackend.models.dtos;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.SwaggerDefinition;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@SwaggerDefinition
public class SoldierDTO {

  @ApiModelProperty(position = 1)
  private Long id;
  @ApiModelProperty(position = 2)
  private int level;
  @ApiModelProperty(position = 3)
  private Timestamp startedAt;
  @ApiModelProperty(position = 4)
  private Timestamp finishedAt;

  public SoldierDTO() {
  }

  public SoldierDTO(Long id, int level, Timestamp startedAt, Timestamp finishedAt) {
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

  public Timestamp getStartedAt() {
    return startedAt;
  }

  public void setStartedAt(Timestamp startedAt) {
    this.startedAt = startedAt;
  }

  public Timestamp getFinishedAt() {
    return finishedAt;
  }

  public void setFinishedAt(Timestamp finishedAt) {
    this.finishedAt = finishedAt;
  }

}
