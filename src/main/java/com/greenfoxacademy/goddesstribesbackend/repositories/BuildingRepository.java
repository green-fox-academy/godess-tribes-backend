package com.greenfoxacademy.goddesstribesbackend.repositories;

import com.greenfoxacademy.goddesstribesbackend.models.entities.Building;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface BuildingRepository extends CrudRepository<Building, Long> {
  ArrayList<Building> findAll();
  ArrayList<Building> findBuildingsByKingdom_Id(Long kingdomId);
  Optional<Building> findBuildingByKingdom_IdAndId(Long kingdomId, Long buildingId);
  int countBuildingsByKingdom_Id(Long kingdomId);
}
