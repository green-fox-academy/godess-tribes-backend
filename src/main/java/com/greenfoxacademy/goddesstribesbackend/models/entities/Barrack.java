package com.greenfoxacademy.goddesstribesbackend.models.entities;

import javax.persistence.Entity;

@Entity
public class Barrack extends Building {

  public Barrack() {
  }

  public Barrack(Kingdom kingdom) {
    super(kingdom);
  }

}
