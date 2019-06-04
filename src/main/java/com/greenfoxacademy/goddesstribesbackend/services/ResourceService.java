package com.greenfoxacademy.goddesstribesbackend.services;

import com.greenfoxacademy.goddesstribesbackend.models.ResourceTypeENUM;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.ResourceDTO;
import com.greenfoxacademy.goddesstribesbackend.models.entities.Kingdom;
import com.greenfoxacademy.goddesstribesbackend.models.entities.Resource;
import com.greenfoxacademy.goddesstribesbackend.models.entities.Townhall;
import com.greenfoxacademy.goddesstribesbackend.repositories.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResourceService {

  private ResourceRepository resourceRepository;

  @Autowired
  public ResourceService(ResourceRepository resourceRepository) {
    this.resourceRepository = resourceRepository;
  }

  public Resource saveFoodAtStart(Townhall townhall) {
    Resource food = new Resource(ResourceTypeENUM.FOOD, Townhall.START_FOOD_AMOUNT, townhall);
    return resourceRepository.save(food);
  }

  public Resource saveGoldAtStart(Townhall townhall) {
    Resource gold = new Resource(ResourceTypeENUM.GOLD, Townhall.START_GOLD_AMOUNT, townhall);
    return resourceRepository.save(gold);
  }

  public Resource save(Resource resource) {
    return resourceRepository.save(resource);
  }

  public ArrayList<Resource> findResourcesByKingdom(Long kingdomId) {
    return resourceRepository.findResourcesByTownhall_Kingdom_Id(kingdomId);
  }

  public Resource findResourceByKingdomAndType(Long kingdomId, ResourceTypeENUM type) {
    return resourceRepository.findResourceByTownhall_Kingdom_IdAndType(kingdomId, type).orElse(null);
  }

}
