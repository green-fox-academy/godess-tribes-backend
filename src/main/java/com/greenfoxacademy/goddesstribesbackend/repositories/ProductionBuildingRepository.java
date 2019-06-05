package com.greenfoxacademy.goddesstribesbackend.repositories;

import com.greenfoxacademy.goddesstribesbackend.models.entities.ProductionBuilding;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface ProductionBuildingRepository extends CrudRepository<ProductionBuilding, Long> {
  ArrayList<ProductionBuilding> findAll();
  ArrayList<ProductionBuilding> findTownhallsByKingdom_Id(Long kingdomId);
  Optional<ProductionBuilding> findById(Long productionBuildingId);
}
