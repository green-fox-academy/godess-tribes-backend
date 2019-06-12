package com.greenfoxacademy.goddesstribesbackend.services;

import com.greenfoxacademy.goddesstribesbackend.models.dtos.SoldierDTO;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.SoldiersDTO;
import com.greenfoxacademy.goddesstribesbackend.models.entities.Soldier;
import com.greenfoxacademy.goddesstribesbackend.repositories.SoldierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
      int soldierConsumptionRate = soldier.getConsumptionRate();
      if (LocalDateTime.now().isBefore(soldier.getFinishedAt())) {
        soldierConsumptionRate -= Soldier.CONSUMPTION_RATE_PER_LEVEL;
      }
      consumptionRate += soldierConsumptionRate;
    }
    return consumptionRate;
  }

  public SoldierDTO createSoldierDTO(Soldier soldier) {
    SoldierDTO soldierDTO = new SoldierDTO();

    soldierDTO.setId(soldier.getId());

    int soldierLevel = soldier.getLevel();
    if (LocalDateTime.now().isBefore(soldier.getFinishedAt())) {
      soldierLevel -= 1;
    }
    soldierDTO.setLevel(soldierLevel);

    Timestamp startedAt = Timestamp.valueOf(soldier.getStartedAt());
    soldierDTO.setStartedAt(startedAt);
    Timestamp finishedAt = Timestamp.valueOf(soldier.getFinishedAt());
    soldierDTO.setFinishedAt(finishedAt);
    return soldierDTO;
  }

  public SoldiersDTO createSoldiersDTO(Long kingdomId) {
    ArrayList<Soldier> soldierList = soldierRepository.findSoldiersByBarrack_Kingdom_Id(kingdomId);
    ArrayList<SoldierDTO> soldierDTOList = new ArrayList<>();

    for (Soldier soldier : soldierList) {
      soldierDTOList.add(createSoldierDTO(soldier));
    }
    return new SoldiersDTO(soldierDTOList);
  }

}
