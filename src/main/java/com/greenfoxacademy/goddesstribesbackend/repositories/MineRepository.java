package com.greenfoxacademy.goddesstribesbackend.repositories;

import com.greenfoxacademy.goddesstribesbackend.models.entities.Mine;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface MineRepository extends CrudRepository<Mine, Long> {
  ArrayList<Mine> findMinesByKingdom_Id(Long kingdomId);
}
