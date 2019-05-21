package com.greenfoxacademy.goddesstribesbackend.services;

import com.greenfoxacademy.goddesstribesbackend.models.entities.*;
import com.greenfoxacademy.goddesstribesbackend.repositories.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BuildingService {

  private BuildingRepository buildingRepository;

  @Autowired
  public BuildingService(BuildingRepository buildingRepository) {
    this.buildingRepository = buildingRepository;
  }

  public Townhall saveTownhall(Kingdom kingdom) {
    if (kingdom != null) {
      Townhall townhall = new Townhall(kingdom, LocalDateTime.now().minusMinutes(Building.CREATION_TIME));
      return buildingRepository.save(townhall);
    }
    return null;
  }

  public Farm saveFarmAtStart(Kingdom kingdom) {
    if (kingdom != null) {
      Farm farm = new Farm(kingdom, LocalDateTime.now().minusMinutes(Building.CREATION_TIME));
      return buildingRepository.save(farm);
    }
    return null;
  }

  public Mine saveMineAtStart(Kingdom kingdom) {
    if (kingdom != null) {
      Mine mine = new Mine(kingdom, LocalDateTime.now().minusMinutes(Building.CREATION_TIME));
      return buildingRepository.save(mine);
    }
    return null;
  }

}
