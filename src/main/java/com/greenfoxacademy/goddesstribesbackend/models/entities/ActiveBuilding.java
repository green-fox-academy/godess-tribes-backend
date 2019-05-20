package com.greenfoxacademy.goddesstribesbackend.models.entities;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public abstract class ActiveBuilding extends Building {

  public static final int CREATION_COST = 250;
  public static final int CREATION_TIME = 2;

  public ActiveBuilding() {
  }

  public ActiveBuilding(Kingdom kingdom) {
    super(kingdom);
    setFinishedAt(getStartedAt().plusMinutes(CREATION_TIME));
  }

  public ActiveBuilding(Kingdom kingdom, LocalDateTime startedAt) {
    super(kingdom, startedAt);
    setFinishedAt(startedAt.plusMinutes(CREATION_TIME));
  }

}
