package com.greenfoxacademy.goddesstribesbackend.services;

import com.greenfoxacademy.goddesstribesbackend.models.ResourceType;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.ResourceDTO;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.ResourceTypeENUM;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.ResourcesDTO;
import com.greenfoxacademy.goddesstribesbackend.models.entities.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

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

  public void updateResources(Long kingdomId) {
    updateFoodResource(kingdomId);
    updateGoldResource(kingdomId);
  }

  private void updateFoodResource(Long kingdomId) {
    Resource foodResource = resourceService.findResourceByKingdomAndType(kingdomId, ResourceType.FOOD);
    int previousAmount = foodResource.getAmount();
    int increment = (int) (foodGenerationRate(kingdomId) * duration(foodResource));
    int currentAmount = previousAmount + increment;
    foodResource.setAmount(currentAmount);
    foodResource.setUpdateTime(LocalDateTime.now());
    resourceService.save(foodResource);
  }

  private void updateGoldResource(Long kingdomId) {
    Resource goldResource = resourceService.findResourceByKingdomAndType(kingdomId, ResourceType.GOLD);
    int previousAmount = goldResource.getAmount();
    int increment = (int) (buildingService.findGoldProductionRate(kingdomId) * duration(goldResource));
    int currentAmount = previousAmount + increment;
    goldResource.setAmount(currentAmount);
    goldResource.setUpdateTime(LocalDateTime.now());
    resourceService.save(goldResource);
  }

  public int foodGenerationRate(Long kingdomId) {
    int foodProductionRate = buildingService.findFoodProductionRate(kingdomId);
    int foodConsumptionRate = soldierService.findFoodConsumptionRate(kingdomId);
    return foodProductionRate - foodConsumptionRate;
  }

  private long duration(Resource resource) {
    LocalDateTime previousUpdateTime = resource.getUpdateTime();
    LocalDateTime now = LocalDateTime.now();
    long duration = Duration.between(previousUpdateTime, now).toMinutes();
    return duration;
  }

  public ResourcesDTO createResourcesDTO(Long kingdomId) {
    updateResources(kingdomId);

    ArrayList<ResourceDTO> resourceDTOS = new ArrayList<>();

    Resource foodResource = resourceService.findResourceByKingdomAndType(kingdomId, ResourceType.FOOD);
    int foodGenerationRate = foodGenerationRate(kingdomId);
    resourceDTOS.add(new ResourceDTO(ResourceTypeENUM.FOOD, foodResource.getAmount(), foodGenerationRate, foodResource.getUpdateTime()));

    Resource goldResource = resourceService.findResourceByKingdomAndType(kingdomId, ResourceType.GOLD);
    int goldGenerationRate = buildingService.findGoldProductionRate(kingdomId);
    resourceDTOS.add(new ResourceDTO(ResourceTypeENUM.GOLD, goldResource.getAmount(), goldGenerationRate, goldResource.getUpdateTime()));

    return new ResourcesDTO(resourceDTOS);
  }

}
