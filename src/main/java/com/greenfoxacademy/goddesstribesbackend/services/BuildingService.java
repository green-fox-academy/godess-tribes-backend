package com.greenfoxacademy.goddesstribesbackend.services;

import com.greenfoxacademy.goddesstribesbackend.models.BuildingTypeENUM;
import com.greenfoxacademy.goddesstribesbackend.models.ResourceTypeENUM;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.BuildingDTO;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.BuildingsDTO;
import com.greenfoxacademy.goddesstribesbackend.models.entities.*;
import com.greenfoxacademy.goddesstribesbackend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingService {

  private KingdomRepository kingdomRepository;
  private BuildingRepository buildingRepository;
  private FarmRepository farmRepository;
  private MineRepository mineRepository;
  private ResourceRepository resourceRepository;
  private TownhallRepository townhallRepository;
  private ProductionBuildingRepository productionBuildingRepository;

  @Autowired
  public BuildingService(KingdomRepository kingdomRepository, BuildingRepository buildingRepository,
                         FarmRepository farmRepository, MineRepository mineRepository,
                         ResourceRepository resourceRepository, TownhallRepository townhallRepository,
                         ProductionBuildingRepository productionBuildingRepository) {
    this.kingdomRepository = kingdomRepository;
    this.buildingRepository = buildingRepository;
    this.farmRepository = farmRepository;
    this.mineRepository = mineRepository;
    this.resourceRepository = resourceRepository;
    this.townhallRepository = townhallRepository;
    this.productionBuildingRepository = productionBuildingRepository;
  }

  public boolean isValidBuildingType(String type) {
    for (BuildingTypeENUM buildingTypeENUM : BuildingTypeENUM.values()) {
      if (buildingTypeENUM.name().equalsIgnoreCase(type)) {
        return true;
      }
    }
    return false;
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

  public ArrayList<Building> findAllBuildings() {
    return buildingRepository.findAll();
  }

  public ArrayList<Building> findBuildingsByKingdom(Long kingdomId) {
    return buildingRepository.findBuildingsByKingdom_Id(kingdomId);
  }

  public Building findBuildingByKingdomAndBuildingId(Long kingdomId, Long buildingId){
    return buildingRepository.findBuildingByKingdom_IdAndId(kingdomId, buildingId).orElse(null);
  }

  public ArrayList<Farm> findFarmsByKingdom(Long kingdomId) {
    return farmRepository.findFarmsByKingdom_Id(kingdomId);
  }

  public ArrayList<Mine> findMinesByKingdom(Long kingdomId) {
    return mineRepository.findMinesByKingdom_Id(kingdomId);
  }

  public int calculateFoodGenerationRate(Long kingdomId) {
    int foodProductionRate = 0;
    ArrayList<Farm> farms = findFarmsByKingdom(kingdomId);

    for (Farm farm : farms) {
      foodProductionRate += farm.getProductionRate();
    }
    return foodProductionRate;
  }

  public int calculateGoldGenerationRate(Long kingdomId) {
    int goldProductionRate = 0;
    ArrayList<Mine> mines = findMinesByKingdom(kingdomId);

    for (Mine mine : mines) {
      goldProductionRate += mine.getProductionRate();
    }
    return goldProductionRate;
  }

  public Building createBuilding(Kingdom kingdom, String type) {
    Resource goldResource = resourceRepository.findResourceByTownhall_Kingdom_IdAndType(kingdom.getId(), ResourceTypeENUM.GOLD).get();
    Building building;

    if (type.equalsIgnoreCase(BuildingTypeENUM.FARM.toString())) {
      building = buildingRepository.save(new Farm(kingdom));
    } else if (type.equalsIgnoreCase(BuildingTypeENUM.MINE.toString())) {
      building = buildingRepository.save(new Mine(kingdom));
    } else if (type.equalsIgnoreCase(BuildingTypeENUM.BARRACK.toString())) {
      building = buildingRepository.save(new Barrack(kingdom));
    } else {
      building = null;
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
    buildingDTO.setType(building.getType());
    buildingDTO.setLevel(building.getLevel());
    Timestamp startedAt = Timestamp.valueOf(building.getStartedAt());
    buildingDTO.setStartedAt(startedAt);
    Timestamp finishedAt = Timestamp.valueOf(building.getFinishedAt());
    buildingDTO.setFinishedAt(finishedAt);
    return buildingDTO;
  }

  public BuildingsDTO createBuildingsDTO(String username) {
    List<BuildingDTO> buildingDTOList = new ArrayList<>();
    Kingdom kingdom = kingdomRepository.findKingdomByUser_Username(username).get();
    ArrayList<Building> buildingList = findBuildingsByKingdom(kingdom.getId());

    for (Building building : buildingList) {
      BuildingDTO buildingDTO = new BuildingDTO();
      buildingDTO.setId(building.getId());
      buildingDTO.setType(building.getType());
      buildingDTO.setLevel(building.getLevel());
      Timestamp startedAt = Timestamp.valueOf(building.getStartedAt());
      buildingDTO.setStartedAt(startedAt);
      Timestamp finishedAt = Timestamp.valueOf(building.getFinishedAt());
      buildingDTO.setFinishedAt(finishedAt);
      buildingDTOList.add(buildingDTO);
    }
    return new BuildingsDTO(buildingDTOList);
  }

  public boolean isValidLevel(Integer upgradeLevelAsked, Integer currentLevel, Long id, BuildingTypeENUM type){

    if (upgradeLevelAsked == null || upgradeLevelAsked < 1 || upgradeLevelAsked > 3) return false;
    if (upgradeLevelAsked == currentLevel) return false;

    Integer townhallLevel = townhallRepository.findById(id).get().getLevel();

    if (!type.equals(BuildingTypeENUM.TOWNHALL)){
      if (upgradeLevelAsked > townhallLevel) return false;
    }

    if (upgradeLevelAsked - currentLevel > 1) return false;

    return true;
  }

  public Building upgradeBuilding(Long kingdomId, Long buildingId, Integer upgradeLevel) {
    Building buildingToUpgrade = findBuildingByKingdomAndBuildingId(kingdomId, buildingId);

    Resource goldResource = resourceRepository.findResourceByTownhall_Kingdom_IdAndType(kingdomId, ResourceTypeENUM.GOLD).get();
    int newGoldAmount = goldResource.getAmount() - buildingToUpgrade.getUpgradeCost();
    goldResource.setAmount(newGoldAmount);
    goldResource.setUpdateTime(LocalDateTime.now());
    resourceRepository.save(goldResource);

    buildingToUpgrade.setLevel(upgradeLevel);
    buildingToUpgrade.setUpgradeCost(Building.UPGRADE_COST_PER_LEVEL * buildingToUpgrade.getLevel());
    buildingToUpgrade.setStartedAt(LocalDateTime.now());
    buildingToUpgrade.setFinishedAt(LocalDateTime.now().plusMinutes(Building.UPGRADE_TIME));
    buildingRepository.save(buildingToUpgrade);

    return buildingToUpgrade;
  }

  public Townhall upgradeTownhall(Long kingdomId, Long buildingId, Integer upgradeLevel){
    upgradeBuilding(kingdomId, buildingId, upgradeLevel);

    Townhall townhallToUpgrade = townhallRepository.findById(buildingId).get();
    townhallToUpgrade.setFoodCapacity(Townhall.FOOD_CAPACITY_PER_LEVEL * townhallToUpgrade.getLevel());
    townhallToUpgrade.setGoldCapacity(Townhall.GOLD_CAPACITY_PER_LEVEL * townhallToUpgrade.getLevel());
    townhallRepository.save(townhallToUpgrade);
    return townhallToUpgrade;
  }

  public ProductionBuilding upgradeProductionBuilding(Long kingdomId, Long buildingId, Integer upgradeLevel) {
    upgradeBuilding(kingdomId, buildingId, upgradeLevel);

    ProductionBuilding prodBuildingToUpgrade = productionBuildingRepository.findById(buildingId).get();
    prodBuildingToUpgrade.setProductionRate(ProductionBuilding.PROD_RATE_PER_LEVEL * prodBuildingToUpgrade.getLevel());
    productionBuildingRepository.save(prodBuildingToUpgrade);
    return prodBuildingToUpgrade;
  }

}
