package com.greenfoxacademy.goddesstribesbackend.repositories;

import com.greenfoxacademy.goddesstribesbackend.models.entities.Building;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface BuildingRepository extends CrudRepository<Building, Long> {

  ArrayList<Building> findAll();

  ArrayList<Building> findBuildingsByKingdom_Id(Long kingdomId);

  int countBuildingsByKingdom_Id(Long kingdomId);

}
