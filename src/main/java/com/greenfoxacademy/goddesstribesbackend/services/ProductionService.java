package com.greenfoxacademy.goddesstribesbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductionService {

  private BuildingService buildingService;
  private ResourceService resourceService;
  private SoldierService soldierService;

  @Autowired
  public ProductionService(BuildingService buildingService, ResourceService resourceService, SoldierService soldierService) {
    this.buildingService = buildingService;
    this.resourceService = resourceService;
    this.soldierService = soldierService;
  }

}
