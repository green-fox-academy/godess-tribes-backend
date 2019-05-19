package com.greenfoxacademy.goddesstribesbackend.models.dtos;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition
public class ResourceDTO {

  @ApiModelProperty(position = 1)
  private ResourceTypeENUM resourceTypeENUM;
  @ApiModelProperty(position = 2)
  private int amount;
  @ApiModelProperty(position = 3)
  private int generationRate;

  public ResourceDTO() {
  }

  public ResourceDTO(ResourceTypeENUM resourceTypeENUM, int amount, int generationRate) {
    this.resourceTypeENUM = resourceTypeENUM;
    this.amount = amount;
    this.generationRate = generationRate;
  }

  public ResourceTypeENUM getResourceTypeENUM() {
    return resourceTypeENUM;
  }

  public void setResourceTypeENUM(ResourceTypeENUM resourceTypeENUM) {
    this.resourceTypeENUM = resourceTypeENUM;
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
}
