package com.greenfoxacademy.goddesstribesbackend.models.dtos;

public class ResourceDTO {

  private ResourceTypeENUM resourceTypeENUM;
  private int amount;
  private int generationRate;

  public ResourceDTO() {
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
