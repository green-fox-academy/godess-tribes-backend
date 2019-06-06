package com.greenfoxacademy.goddesstribesbackend.services;

import com.greenfoxacademy.goddesstribesbackend.models.dtos.SoldierDTO;
import com.greenfoxacademy.goddesstribesbackend.models.entities.Soldier;
import com.greenfoxacademy.goddesstribesbackend.repositories.SoldierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class SoldierService {

  private SoldierRepository soldierRepository;

  @Autowired
  public SoldierService(SoldierRepository soldierRepository) {
    this.soldierRepository = soldierRepository;
  }

  public ArrayList<Soldier> findSoldiersByKingdom(Long kingdomId) {
    return soldierRepository.findSoldiersByBarrack_Kingdom_Id(kingdomId);
  }

  public int calculateFoodConsumptionRate(Long kingdomId) {
    int consumptionRate = 0;
    ArrayList<Soldier> soldiers = findSoldiersByKingdom(kingdomId);

    for (Soldier soldier : soldiers) {
      consumptionRate += soldier.getConsumptionRate();
    }
    return consumptionRate;
  }

  public List<SoldierDTO> createSoldierDTOList(Long kingdomId) {
    ArrayList<Soldier> soldiers = soldierRepository.findSoldiersByBarrack_Kingdom_Id(kingdomId);
    ArrayList<SoldierDTO> listOfSoldiers = new ArrayList<>();

    for (Soldier soldier : soldiers) {
      SoldierDTO soldierDTO = new SoldierDTO(soldier.getId(), soldier.getLevel(), Timestamp.valueOf(soldier.getStartedAt()), Timestamp.valueOf(soldier.getFinishedAt()));
      listOfSoldiers.add(soldierDTO);
    }
    return listOfSoldiers;
  }

}
