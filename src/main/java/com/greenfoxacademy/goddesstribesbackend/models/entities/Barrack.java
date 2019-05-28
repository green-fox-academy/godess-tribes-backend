package com.greenfoxacademy.goddesstribesbackend.models.entities;

import com.greenfoxacademy.goddesstribesbackend.models.BuildingTypeENUM;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Barrack extends Building {

  @OneToMany(mappedBy = "barrack")
  private List<Soldier> soldiers;

  public Barrack() {
  }

  public Barrack(Kingdom kingdom) {
    super(kingdom);
    this.setBuildingType(BuildingTypeENUM.BARRACK);
  }

  public List<Soldier> getSoldiers() {
    return soldiers;
  }

  public void setSoldiers(List<Soldier> soldiers) {
    this.soldiers = soldiers;
  }
}
