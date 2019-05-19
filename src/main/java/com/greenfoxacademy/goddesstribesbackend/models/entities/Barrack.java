package com.greenfoxacademy.goddesstribesbackend.models.entities;

import javax.persistence.Entity;

@Entity
public class Barrack extends ActiveBuilding {

  public Barrack() {
  }

  public Barrack(Kingdom kingdom) {
    super(kingdom);
  }

}
