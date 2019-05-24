package com.greenfoxacademy.goddesstribesbackend.services;

import com.greenfoxacademy.goddesstribesbackend.models.ResourceTypeENUM;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.ResourceDTO;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.ResourcesDTO;
import com.greenfoxacademy.goddesstribesbackend.models.entities.Building;
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

  public void updateResources(Long kingdomId){
    updateFoodResource(kingdomId);
    updateGoldResource(kingdomId);
  }

  public void updateFoodResource(Long kingdomId) {
    Resource foodResource = resourceService.findResourceByKingdomAndType(kingdomId, ResourceTypeENUM.FOOD);

    if (foodResource != null) {
      int previousAmount = foodResource.getAmount();
      int increment = (int) (calculateNetFoodGenerationRate(kingdomId) * calculateDuration(foodResource) / 60.);
      int maxAmount = foodResource.getTownhall().getFoodCapacity();
      int currentAmount = previousAmount + increment;
      currentAmount = currentAmount <= maxAmount ? currentAmount : maxAmount;

      foodResource.setAmount(currentAmount);
      foodResource.setUpdateTime(LocalDateTime.now());
      resourceService.save(foodResource);
    }
  }

  public void updateGoldResource(Long kingdomId) {
    Resource goldResource = resourceService.findResourceByKingdomAndType(kingdomId, ResourceTypeENUM.GOLD);

    if (goldResource != null) {
      int previousAmount = goldResource.getAmount();
      int increment = (int) (buildingService.calculateGoldGenerationRate(kingdomId) * calculateDuration(goldResource) / 60.);
      int maxAmount = goldResource.getTownhall().getGoldCapacity();
      int currentAmount = previousAmount + increment;
      currentAmount = currentAmount <= maxAmount ? currentAmount : maxAmount;

      goldResource.setAmount(currentAmount);
      goldResource.setUpdateTime(LocalDateTime.now());
      resourceService.save(goldResource);
    }
  }

  public int calculateNetFoodGenerationRate(Long kingdomId) {
    int foodGenerationRate = buildingService.calculateFoodGenerationRate(kingdomId);
    int foodConsumptionRate = soldierService.calculateFoodConsumptionRate(kingdomId);
    return foodGenerationRate - foodConsumptionRate;
  }

  private long calculateDuration(Resource resource) {
    LocalDateTime previousUpdateTime = resource.getUpdateTime();
    LocalDateTime now = LocalDateTime.now();
    long duration = Duration.between(previousUpdateTime, now).getSeconds();
    return duration;
  }

  public int calculateGoldReserve(Long kingdomId) {
    updateGoldResource(kingdomId);
    Resource goldResource = resourceService.findResourceByKingdomAndType(kingdomId, ResourceTypeENUM.GOLD);
    return goldResource.getAmount();
  }

  public boolean isEnoughMoneyToCreateBuilding(Long kingdomId) {
    return calculateGoldReserve(kingdomId) >= Building.CREATION_COST;
  }

  public ResourcesDTO createResourcesDTO(Long kingdomId) {
    updateResources(kingdomId);

    ArrayList<ResourceDTO> resourceDTOlist = new ArrayList<>();

    Resource foodResource = resourceService.findResourceByKingdomAndType(kingdomId, ResourceTypeENUM.FOOD);
    int foodNetGenerationRate = calculateNetFoodGenerationRate(kingdomId);
    resourceDTOlist.add(new ResourceDTO(ResourceTypeENUM.FOOD, foodResource.getAmount(), foodNetGenerationRate));

    Resource goldResource = resourceService.findResourceByKingdomAndType(kingdomId, ResourceTypeENUM.GOLD);
    int goldGenerationRate = buildingService.calculateGoldGenerationRate(kingdomId);
    resourceDTOlist.add(new ResourceDTO(ResourceTypeENUM.GOLD, goldResource.getAmount(), goldGenerationRate));

    return new ResourcesDTO(resourceDTOlist);
  }

}
