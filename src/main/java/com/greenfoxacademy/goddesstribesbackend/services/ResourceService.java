package com.greenfoxacademy.goddesstribesbackend.services;

import com.greenfoxacademy.goddesstribesbackend.models.ResourceType;
import com.greenfoxacademy.goddesstribesbackend.models.entities.Resource;
import com.greenfoxacademy.goddesstribesbackend.models.entities.Townhall;
import com.greenfoxacademy.goddesstribesbackend.repositories.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {

  private ResourceRepository resourceRepository;

  @Autowired
  public ResourceService(ResourceRepository resourceRepository) {
    this.resourceRepository = resourceRepository;
  }

  public Resource saveFoodAtStart(Townhall townhall) {
    Resource food = new Resource(ResourceType.FOOD, Townhall.START_FOOD_AMOUNT, townhall);
    return resourceRepository.save(food);
  }

  public Resource saveGoldAtStart(Townhall townhall) {
    Resource gold = new Resource(ResourceType.GOLD, Townhall.START_GOLD_AMOUNT, townhall);
    return resourceRepository.save(gold);
  }

}
