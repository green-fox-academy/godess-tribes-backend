package com.greenfoxacademy.goddesstribesbackend.models.entities;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public abstract class ActiveBuilding extends Building {

  public static final int CREATIONCOST = 250;
  public static final int CREATIONTIME = 2;

  public ActiveBuilding() {
  }

  public ActiveBuilding(Kingdom kingdom) {
    super(kingdom);
    setFinishedAt(getStartedAt().plusMinutes(CREATIONTIME));
  }

  public ActiveBuilding(Kingdom kingdom, LocalDateTime startedAt) {
    super(kingdom, startedAt);
    setFinishedAt(startedAt.plusMinutes(CREATIONTIME));
  }

}
