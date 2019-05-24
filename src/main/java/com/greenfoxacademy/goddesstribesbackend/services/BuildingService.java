package com.greenfoxacademy.goddesstribesbackend.services;

import com.greenfoxacademy.goddesstribesbackend.models.dtos.BuildingDTO;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.BuildingsDTO;
import com.greenfoxacademy.goddesstribesbackend.models.entities.*;
import com.greenfoxacademy.goddesstribesbackend.repositories.BuildingRepository;
import com.greenfoxacademy.goddesstribesbackend.repositories.KingdomRepository;
import com.greenfoxacademy.goddesstribesbackend.repositories.UserRepository;
import com.greenfoxacademy.goddesstribesbackend.repositories.FarmRepository;
import com.greenfoxacademy.goddesstribesbackend.repositories.MineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BuildingService {

  private BuildingRepository buildingRepository;
  private FarmRepository farmRepository;
  private MineRepository mineRepository;
  private UserRepository userRepository;
  private KingdomRepository kingdomRepository;

  @Autowired
  public BuildingService(BuildingRepository buildingRepository, FarmRepository farmRepository, MineRepository mineRepository, UserRepository userRepository, KingdomRepository kingdomRepository) {
    this.buildingRepository = buildingRepository;
    this.farmRepository = farmRepository;
    this.mineRepository = mineRepository;
    this.userRepository = userRepository;
    this.kingdomRepository = kingdomRepository;
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

  public ArrayList<Building> findAll(){
    return buildingRepository.findAll();
  }
  
  public ArrayList<Building> findBuildingsByKingdom(Long kingdomId){
    return buildingRepository.findBuildingsByKingdom_Id(kingdomId);
  }

  public BuildingsDTO createBuildingsDTO(String username){
    List<BuildingDTO> buildingDTOList = new ArrayList<>();
    if (kingdomRepository.findKingdomByUser_Username(username).isPresent()){
      Kingdom kingdom = kingdomRepository.findKingdomByUser_Username(username).get();
      ArrayList<Building> buildingList = findBuildingsByKingdom(kingdom.getId());
      for (Building building: buildingList){
        BuildingDTO buildingDTO = new BuildingDTO();
        buildingDTO.setId(building.getId());
        buildingDTO.setBuildingTypeENUM(building.getBuildingType());
        buildingDTO.setLevel(building.getLevel());
        buildingDTO.setStartedAt(building.getStartedAt());
        buildingDTO.setFinishedAt(building.getFinishedAt());
        buildingDTOList.add(buildingDTO);
      }
    }
    return new BuildingsDTO(buildingDTOList);
  }

}
