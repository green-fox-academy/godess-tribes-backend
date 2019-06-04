package com.greenfoxacademy.goddesstribesbackend.repositories;

import com.greenfoxacademy.goddesstribesbackend.models.entities.Mine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MineRepository extends CrudRepository<Mine, Long> {
  ArrayList<Mine> findAll();
  ArrayList<Mine> findMinesByKingdom_Id(Long kingdomId);
}
