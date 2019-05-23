package com.greenfoxacademy.goddesstribesbackend.repositories;

import com.greenfoxacademy.goddesstribesbackend.models.entities.Soldier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface SoldierRepository extends CrudRepository<Soldier, Long> {
  ArrayList<Soldier> findSoldiersByBarrack_Kingdom_Id(Long kingdomId);
  int countSoldiersByBarrack_Kingdom_Id(Long kingdomId);
}
