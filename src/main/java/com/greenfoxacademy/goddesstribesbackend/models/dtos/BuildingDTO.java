package com.greenfoxacademy.goddesstribesbackend.models.dtos;

import com.greenfoxacademy.goddesstribesbackend.models.BuildingTypeENUM;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.SwaggerDefinition;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@SwaggerDefinition
public class BuildingDTO {
  @ApiModelProperty(position = 1)
  private Long id;
  @ApiModelProperty(position = 2)
  private BuildingTypeENUM buildingTypeENUM;
  @ApiModelProperty(position = 3)
  private int level;
  @ApiModelProperty(position = 4)
  private Timestamp startedAt;
  @ApiModelProperty(position = 5)
  private Timestamp finishedAt;

  public BuildingDTO() {
  }

  public BuildingDTO(Long id, BuildingTypeENUM buildingTypeENUM, int level, Timestamp startedAt, Timestamp finishedAt) {
    this.id = id;
    this.buildingTypeENUM = buildingTypeENUM;
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

  public BuildingTypeENUM getBuildingTypeENUM() {
    return buildingTypeENUM;
  }

  public void setBuildingTypeENUM(BuildingTypeENUM buildingTypeENUM) {
    this.buildingTypeENUM = buildingTypeENUM;
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
