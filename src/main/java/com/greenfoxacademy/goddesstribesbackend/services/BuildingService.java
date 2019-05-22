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
    Townhall townhall = new Townhall(kingdom, LocalDateTime.now().minusMinutes(Building.CREATION_TIME));
    return buildingRepository.save(townhall);
  }

  public Farm saveFarmAtStart(Kingdom kingdom) {
    Farm farm = new Farm(kingdom, LocalDateTime.now().minusMinutes(Building.CREATION_TIME));
    return buildingRepository.save(farm);
  }

  public Mine saveMineAtStart(Kingdom kingdom) {
    Mine mine = new Mine(kingdom, LocalDateTime.now().minusMinutes(Building.CREATION_TIME));
    return buildingRepository.save(mine);
  }

  public ArrayList<Farm> findFarms(Long kingdomId) {
    return farmRepository.findFarmsByKingdom_Id(kingdomId);
  }

  public ArrayList<Mine> findMines(Long kingdomId) {
    return mineRepository.findMinesByKingdom_Id(kingdomId);
  }

  public int findFoodProductionRate(Long kingdomId) {
    int foodProductionRate = 0;
    ArrayList<Farm> farms = findFarms(kingdomId);

    for (Farm farm : farms) {
      foodProductionRate += farm.getProductionRate();
    }
    return foodProductionRate;
  }

  public int findGoldProductionRate(Long kingdomId) {
    int goldProductionRate = 0;
    ArrayList<Mine> mines = findMines(kingdomId);

    for (Mine mine : mines) {
      goldProductionRate += mine.getProductionRate();
    }
    return goldProductionRate;
  }

}
