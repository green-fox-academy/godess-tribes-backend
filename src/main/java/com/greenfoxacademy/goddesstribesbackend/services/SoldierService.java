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

  public ArrayList<Soldier> findSoldiers(Long kingdomId) {
    return soldierRepository.findSoldiersByBarrack_Kingdom_Id(kingdomId);
  }

  public int calculateFoodConsumptionRate(Long kingdomId) {
    int consumptionRate = 0;
    ArrayList<Soldier> soldiers = findSoldiers(kingdomId);

    for (Soldier soldier : soldiers) {
      consumptionRate += soldier.getConsumptionRate();
    }
    return consumptionRate;
  }

}
