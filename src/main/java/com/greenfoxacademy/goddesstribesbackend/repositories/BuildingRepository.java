package com.greenfoxacademy.goddesstribesbackend.repositories;

import com.greenfoxacademy.goddesstribesbackend.models.entities.Building;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BuildingRepository extends CrudRepository<Building, Long> {
  ArrayList<Building> findBuildingsByKingdom_Id(Long kingdomId);
  int countBuildingsByKingdom_Id(Long kingdomId);
}
