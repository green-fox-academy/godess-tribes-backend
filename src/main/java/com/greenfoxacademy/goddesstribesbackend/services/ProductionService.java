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
    ArrayList<Resource> resources = resourceService.findResourcesByKingdom(kingdomId);

    for (Resource resource : resources) {
      int previousAmount = resource.getAmount();
      int generationRate = 0;

      if (resource.getType().equals(ResourceType.FOOD)) {
        generationRate = findNetFoodProductionRate(kingdomId);

      } else if (resource.getType().equals(ResourceType.GOLD)) {
        generationRate = buildingService.findGoldProductionRate(kingdomId);
      }

      int currentAmount = previousAmount + (int) (generationRate * findDuration(resource) / 60.);
      resource.setAmount(currentAmount);
      resource.setUpdateTime(LocalDateTime.now());
      resourceService.save(resource);
    }
  }

  public int findNetFoodProductionRate(Long kingdomId) {
    int foodProductionRate = buildingService.findFoodProductionRate(kingdomId);
    int foodConsumptionRate = soldierService.findFoodConsumptionRate(kingdomId);
    return foodProductionRate - foodConsumptionRate;
  }

  private long findDuration(Resource resource) {
    LocalDateTime previousUpdateTime = resource.getUpdateTime();
    LocalDateTime now = LocalDateTime.now();
    long duration = Duration.between(previousUpdateTime, now).getSeconds();
    return duration;
  }

  public ResourcesDTO createResourcesDTO(Long kingdomId) {
    updateResources(kingdomId);

    ArrayList<ResourceDTO> resourceDTOS = new ArrayList<>();

    Resource foodResource = resourceService.findResourceByKingdomAndType(kingdomId, ResourceType.FOOD);
    int foodGenerationRate = findNetFoodProductionRate(kingdomId);
    resourceDTOS.add(new ResourceDTO(ResourceTypeENUM.FOOD, foodResource.getAmount(), foodGenerationRate, foodResource.getUpdateTime()));

    Resource goldResource = resourceService.findResourceByKingdomAndType(kingdomId, ResourceType.GOLD);
    int goldGenerationRate = buildingService.findGoldProductionRate(kingdomId);
    resourceDTOS.add(new ResourceDTO(ResourceTypeENUM.GOLD, goldResource.getAmount(), goldGenerationRate, goldResource.getUpdateTime()));

    return new ResourcesDTO(resourceDTOS);
  }

}
