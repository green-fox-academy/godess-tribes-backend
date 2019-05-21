package com.greenfoxacademy.goddesstribesbackend.models.dtos;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.SwaggerDefinition;

import java.time.LocalDateTime;

@SwaggerDefinition
public class ResourceDTO {

  @ApiModelProperty(position = 1)
  private ResourceTypeENUM type;
  @ApiModelProperty(position = 2)
  private int amount;
  @ApiModelProperty(position = 3)
  private int generationRate;
  @ApiModelProperty(position = 4)
  private LocalDateTime updatedAt;

  public ResourceDTO() {
  }

  public ResourceDTO(ResourceTypeENUM type, int amount, int generationRate, LocalDateTime updatedAt) {
    this.type = type;
    this.amount = amount;
    this.generationRate = generationRate;
    this.updatedAt = updatedAt;
  }

  public ResourceTypeENUM getType() {
    return type;
  }

  public void setType(ResourceTypeENUM type) {
    this.type = type;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public int getGenerationRate() {
    return generationRate;
  }

  public void setGenerationRate(int generationRate) {
    this.generationRate = generationRate;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

}
