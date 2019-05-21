package com.greenfoxacademy.goddesstribesbackend.services;

import com.greenfoxacademy.goddesstribesbackend.models.entities.Soldier;
import com.greenfoxacademy.goddesstribesbackend.repositories.SoldierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

  public int findConsumptionRateByKingdom(Long kingdomId) {
    int consumptionRate = 0;
    ArrayList<Soldier> soldiers = findSoldiersByKingdom(kingdomId);

    for (Soldier soldier : soldiers) {
      consumptionRate += soldier.getConsumptionRate();
    }
    return consumptionRate;
  }

}
