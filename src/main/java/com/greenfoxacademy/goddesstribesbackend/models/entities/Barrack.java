package com.greenfoxacademy.goddesstribesbackend.models.entities;

import com.greenfoxacademy.goddesstribesbackend.models.BuildingTypeENUM;

import javax.persistence.Entity;

@Entity
public class Barrack extends Building {

  public Barrack() {
  }

  public Barrack(Kingdom kingdom) {
    super(kingdom);
    this.setBuildingType(BuildingTypeENUM.BARRACK);
  }

}
