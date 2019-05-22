package com.greenfoxacademy.goddesstribesbackend.repositories;

import com.greenfoxacademy.goddesstribesbackend.models.entities.Building;
import com.greenfoxacademy.goddesstribesbackend.models.entities.Kingdom;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface BuildingRepository extends CrudRepository<Building, Long> {

  ArrayList<Building> findAll();

  ArrayList<Building> findBuildingsByKingdom_Id(Long id);
}
