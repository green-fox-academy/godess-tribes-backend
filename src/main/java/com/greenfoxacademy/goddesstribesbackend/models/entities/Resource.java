package com.greenfoxacademy.goddesstribesbackend.models.entities;

import com.greenfoxacademy.goddesstribesbackend.models.ResourceType;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Resource {

  private ResourceType type;
  private int amount;
  private LocalDateTime updateTime;

  @ManyToOne(optional = false)
  private Townhall townhall;

  public Resource() {
  }

  public Resource(ResourceType type, int amount, Townhall townhall) {
    this.type = type;
    this.amount = amount;
    updateTime = LocalDateTime.now();
    this.townhall = townhall;
  }

  public ResourceType getType() {
    return type;
  }

  public void setType(ResourceType type) {
    this.type = type;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
  }

  public Townhall getTownhall() {
    return townhall;
  }

  public void setTownhall(Townhall townhall) {
    this.townhall = townhall;
  }

}
