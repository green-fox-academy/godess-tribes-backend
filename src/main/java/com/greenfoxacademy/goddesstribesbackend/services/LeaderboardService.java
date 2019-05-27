package com.greenfoxacademy.goddesstribesbackend.services;

import com.greenfoxacademy.goddesstribesbackend.models.dtos.LeaderboardBuildingsDTO;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.LeaderboardBySoldiersDTO;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.LeaderboardByBuildingsDTO;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.LeaderboardSoldiersDTO;
import com.greenfoxacademy.goddesstribesbackend.models.entities.Kingdom;
import com.greenfoxacademy.goddesstribesbackend.repositories.BuildingRepository;
import com.greenfoxacademy.goddesstribesbackend.repositories.KingdomRepository;
import com.greenfoxacademy.goddesstribesbackend.repositories.SoldierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeaderboardService {

  private BuildingRepository buildingRepository;
  private KingdomRepository kingdomRepository;
  private SoldierRepository soldierRepository;

  @Autowired
  public LeaderboardService(BuildingRepository buildingRepository, KingdomRepository kingdomRepository, SoldierRepository soldierRepository) {
    this.buildingRepository = buildingRepository;
    this.kingdomRepository = kingdomRepository;
    this.soldierRepository = soldierRepository;
  }

  public LeaderboardByBuildingsDTO createLeaderboardByBuildings() {
    List<LeaderboardBuildingsDTO> leaderboardBuildingsDTOList = new ArrayList<>();
    ArrayList<Kingdom> kingdomList = kingdomRepository.findAll();
    for (Kingdom kingdom : kingdomList) {
      LeaderboardBuildingsDTO leaderboardBuildingsDTO = new LeaderboardBuildingsDTO();
      leaderboardBuildingsDTO.setKingdomName(kingdom.getKingdomName());
      leaderboardBuildingsDTO.setBuildings(buildingRepository.countBuildingsByKingdom_Id(kingdom.getId()));
      leaderboardBuildingsDTOList.add(leaderboardBuildingsDTO);
    }
    return new LeaderboardByBuildingsDTO(leaderboardBuildingsDTOList);
  }

  public LeaderboardBySoldiersDTO createLeaderboardBySoldiers() {
    List<LeaderboardSoldiersDTO> leaderboardBySoldiersDTOList = new ArrayList<>();
    ArrayList<Kingdom> kingdomList = kingdomRepository.findAll();
    for (Kingdom kingdom : kingdomList) {
      LeaderboardSoldiersDTO leaderboardSoldiersDTO = new LeaderboardSoldiersDTO();
      leaderboardSoldiersDTO.setKingdomName(kingdom.getKingdomName());
      leaderboardSoldiersDTO.setSoldiers(soldierRepository.countSoldiersByBarrack_Kingdom_Id(kingdom.getId()));
      leaderboardBySoldiersDTOList.add(leaderboardSoldiersDTO);
    }
    return new LeaderboardBySoldiersDTO(leaderboardBySoldiersDTOList);
  }
}
