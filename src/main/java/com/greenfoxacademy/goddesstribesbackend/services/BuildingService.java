package com.greenfoxacademy.goddesstribesbackend.services;

import com.greenfoxacademy.goddesstribesbackend.models.entities.*;
import com.greenfoxacademy.goddesstribesbackend.repositories.BuildingRepository;
import com.greenfoxacademy.goddesstribesbackend.repositories.FarmRepository;
import com.greenfoxacademy.goddesstribesbackend.repositories.MineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class BuildingService {

  private BuildingRepository buildingRepository;
  private FarmRepository farmRepository;
  private MineRepository mineRepository;

  @Autowired
  public BuildingService(BuildingRepository buildingRepository,
                         FarmRepository farmRepository, MineRepository mineRepository) {
    this.buildingRepository = buildingRepository;
    this.farmRepository = farmRepository;
    this.mineRepository = mineRepository;
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

  public ArrayList<Farm> findFarmsByKingdom(Long kingdomId) {
    return farmRepository.findFarmsByKingdom_Id(kingdomId);
  }

  public ArrayList<Mine> findMinesByKingdom(Long kingdomId) {
    return mineRepository.findMinesByKingdom_Id(kingdomId);
  }

  public int findFoodProductionRateByKingdom(Long kingdomId) {
    int foodProductionRate = 0;
    ArrayList<Farm> farms = findFarmsByKingdom(kingdomId);

    for (Farm farm : farms) {
      foodProductionRate += farm.getProductionRate();
    }
    return foodProductionRate;
  }

  public int findGoldProductionRateByKingdom(Long kingdomId) {
    int goldProductionRate = 0;
    ArrayList<Mine> mines = findMinesByKingdom(kingdomId);

    for (Mine mine : mines) {
      goldProductionRate += mine.getProductionRate();
    }
    return goldProductionRate;
  }

}
