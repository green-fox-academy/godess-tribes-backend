package com.greenfoxacademy.goddesstribesbackend.repositories;

import com.greenfoxacademy.goddesstribesbackend.models.entities.Farm;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface FarmRepository extends CrudRepository<Farm, Long> {
  ArrayList<Farm> findFarmsByKingdom_Id(Long kingdomId);
}
