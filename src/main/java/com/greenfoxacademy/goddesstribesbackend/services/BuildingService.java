package com.greenfoxacademy.goddesstribesbackend.services;

import com.greenfoxacademy.goddesstribesbackend.models.BuildingTypeENUM;
import com.greenfoxacademy.goddesstribesbackend.models.ResourceType;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.BuildingDTO;
import com.greenfoxacademy.goddesstribesbackend.models.entities.*;
import com.greenfoxacademy.goddesstribesbackend.repositories.BuildingRepository;
import com.greenfoxacademy.goddesstribesbackend.repositories.FarmRepository;
import com.greenfoxacademy.goddesstribesbackend.repositories.MineRepository;
import com.greenfoxacademy.goddesstribesbackend.repositories.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class BuildingService {

  private BuildingRepository buildingRepository;
  private FarmRepository farmRepository;
  private MineRepository mineRepository;
  private ResourceRepository resourceRepository;

  @Autowired
  public BuildingService(BuildingRepository buildingRepository,
                         FarmRepository farmRepository, MineRepository mineRepository,
                         ResourceRepository resourceRepository) {
    this.buildingRepository = buildingRepository;
    this.farmRepository = farmRepository;
    this.mineRepository = mineRepository;
    this.resourceRepository = resourceRepository;
  }

  public boolean isValidBuildingType(String type) {
    return type.equalsIgnoreCase(BuildingTypeENUM.FARM.toString()) ||
            type.equalsIgnoreCase(BuildingTypeENUM.MINE.toString()) ||
            type.equalsIgnoreCase(BuildingTypeENUM.BARRACK.toString());
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

  public int calculateFoodGenerationRate(Long kingdomId) {
    int foodProductionRate = 0;
    ArrayList<Farm> farms = findFarms(kingdomId);

    for (Farm farm : farms) {
      foodProductionRate += farm.getProductionRate();
    }
    return foodProductionRate;
  }

  public int calculateGoldGenerationRate(Long kingdomId) {
    int goldProductionRate = 0;
    ArrayList<Mine> mines = findMines(kingdomId);

    for (Mine mine : mines) {
      goldProductionRate += mine.getProductionRate();
    }
    return goldProductionRate;
  }

  public Building createBuilding(Kingdom kingdom, String type) {
    if (!isValidBuildingType(type)) return null;
    Resource goldResource = resourceRepository.findResourceByTownhall_Kingdom_IdAndType(kingdom.getId(), ResourceType.GOLD).orElse(null);
    if (goldResource == null) return null;
    if (goldResource.getAmount() < Building.CREATION_COST) return null;

    Building building = null;

    if (type.equalsIgnoreCase(BuildingTypeENUM.FARM.toString())) {
      building = buildingRepository.save(new Farm(kingdom));
    } else if (type.equalsIgnoreCase(BuildingTypeENUM.MINE.toString())) {
      building = buildingRepository.save(new Mine(kingdom));
    } else if (type.equalsIgnoreCase(BuildingTypeENUM.BARRACK.toString())) {
      building = buildingRepository.save(new Barrack(kingdom));
    }

    int newGoldAmount = goldResource.getAmount() - Building.CREATION_COST;
    goldResource.setAmount(newGoldAmount);
    goldResource.setUpdateTime(LocalDateTime.now());
    resourceRepository.save(goldResource);
    return building;
  }

  public BuildingDTO createBuildingDTO(Building building) {
    BuildingDTO buildingDTO = new BuildingDTO();
    buildingDTO.setId(building.getId());
    //buildingDTO.setType(); TODO!
    buildingDTO.setLevel(building.getLevel());
    buildingDTO.setStartedAt(building.getStartedAt());
    buildingDTO.setFinishedAt(building.getFinishedAt());
    return buildingDTO;
  }

}
