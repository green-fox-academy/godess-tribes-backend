package com.greenfoxacademy.goddesstribesbackend.services;

import com.greenfoxacademy.goddesstribesbackend.models.BuildingTypeENUM;
import com.greenfoxacademy.goddesstribesbackend.models.ResourceTypeENUM;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.BuildingDTO;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.BuildingsDTO;
import com.greenfoxacademy.goddesstribesbackend.models.entities.*;
import com.greenfoxacademy.goddesstribesbackend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

  @Autowired
  public BuildingService(KingdomRepository kingdomRepository, BuildingRepository buildingRepository,
                         FarmRepository farmRepository, MineRepository mineRepository,
                         ResourceRepository resourceRepository) {
    this.kingdomRepository = kingdomRepository;
    this.buildingRepository = buildingRepository;
    this.farmRepository = farmRepository;
    this.mineRepository = mineRepository;
    this.resourceRepository = resourceRepository;
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
    buildingDTO.setStartedAt(building.getStartedAt());
    buildingDTO.setFinishedAt(building.getFinishedAt());
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
      buildingDTO.setStartedAt(building.getStartedAt());
      buildingDTO.setFinishedAt(building.getFinishedAt());
      buildingDTOList.add(buildingDTO);
    }
    return new BuildingsDTO(buildingDTOList);
  }

  public List<BuildingDTO> createBuildingDTOList(Kingdom kingdom){
    ArrayList<Building> buildings = buildingRepository.findBuildingsByKingdom_Id(kingdom.getId());
    ArrayList<BuildingDTO> listOfBuildings = new ArrayList<>();

    for (Building building:buildings){
      BuildingDTO buildingDTO = new BuildingDTO(building.getId(),building.getBuildingType(), building.getLevel(), building.getStartedAt(), building.getFinishedAt());
      listOfBuildings.add(buildingDTO);
    }
    return listOfBuildings;
  }
}
