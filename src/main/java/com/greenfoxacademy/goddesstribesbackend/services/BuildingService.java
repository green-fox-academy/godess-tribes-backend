package com.greenfoxacademy.goddesstribesbackend.services;

import com.greenfoxacademy.goddesstribesbackend.models.dtos.BuildingDTO;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.BuildingsDTO;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.BuldingTypeENUM;
import com.greenfoxacademy.goddesstribesbackend.models.entities.*;
import com.greenfoxacademy.goddesstribesbackend.repositories.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BuildingService {

  private BuildingRepository buildingRepository;

  @Autowired
  public BuildingService(BuildingRepository buildingRepository) {
    this.buildingRepository = buildingRepository;
  }

  public Townhall saveTownhall(Kingdom kingdom) {
    if (kingdom != null) {
      Townhall townhall = new Townhall(kingdom);
      return buildingRepository.save(townhall);
    }
    return null;
  }

  public Farm saveFarmAtStart(Kingdom kingdom) {
    if (kingdom != null) {
      Farm farm = new Farm(kingdom, LocalDateTime.now().minusMinutes(ActiveBuilding.CREATION_TIME));
      return buildingRepository.save(farm);
    }
    return null;
  }

  public Mine saveMineAtStart(Kingdom kingdom) {
    if (kingdom != null) {
      Mine mine = new Mine(kingdom, LocalDateTime.now().minusMinutes(ActiveBuilding.CREATION_TIME));
      return buildingRepository.save(mine);
    }
    return null;
  }

  public ArrayList<Building> findAll(){
    ArrayList<Building> buildingList = new ArrayList<>();
    buildingRepository.findAll().forEach(buildingList::add);
   return buildingList;
  }

  public BuildingsDTO createBuildingsDTO(){
    BuildingsDTO buildingsDTO = new BuildingsDTO();
    List<BuildingDTO> buildingDTOList = new ArrayList<>();
    ArrayList<Building> buildingList = findAll();
    for (Building building: buildingList){
     BuildingDTO  buildingDTO = new BuildingDTO();
     buildingDTO.setId(building.getId());

     if (building instanceof Townhall){
       buildingDTO.setBuldingTypeENUM(BuldingTypeENUM.TOWNHALL);
     }

     if (building instanceof Mine){
       buildingDTO.setBuldingTypeENUM(BuldingTypeENUM.MINE);
     }

     if (building instanceof Farm){
       buildingDTO.setBuldingTypeENUM(BuldingTypeENUM.FARM);
     }

     if (building instanceof Barrack){
       buildingDTO.setBuldingTypeENUM(BuldingTypeENUM.BARRACK);
     }
     buildingDTO.setLevel(building.getLevel());
     buildingDTO.setStartedAt(building.getStartedAt());
     buildingDTO.setFinishedAt(building.getFinishedAt());
     buildingDTOList.add(buildingDTO);
    }
    buildingsDTO.setBuildingDTOS(buildingDTOList);
    return buildingsDTO;
  }

}
