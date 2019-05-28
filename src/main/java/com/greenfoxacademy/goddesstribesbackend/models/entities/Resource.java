package com.greenfoxacademy.goddesstribesbackend.models.entities;

import com.greenfoxacademy.goddesstribesbackend.models.ResourceTypeENUM;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Resource {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private ResourceTypeENUM type;
  private int amount;
  private LocalDateTime updateTime;

  @ManyToOne(optional = false)
  @JoinColumn(name = "townhallId")
  private Townhall townhall;

  public Resource() {
  }

  public Resource(ResourceTypeENUM type, int amount, Townhall townhall) {
    this.type = type;
    this.amount = amount;
    updateTime = LocalDateTime.now();
    this.townhall = townhall;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
