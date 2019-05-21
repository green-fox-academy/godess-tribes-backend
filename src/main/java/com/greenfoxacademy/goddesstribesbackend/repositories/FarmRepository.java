package com.greenfoxacademy.goddesstribesbackend.repositories;

import com.greenfoxacademy.goddesstribesbackend.models.entities.Farm;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface FarmRepository extends CrudRepository<Farm, Long> {
  ArrayList<Farm> findFarmsByKingdom_Id(Long kingdomId);
}
