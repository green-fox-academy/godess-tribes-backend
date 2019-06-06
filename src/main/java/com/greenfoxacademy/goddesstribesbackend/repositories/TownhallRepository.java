package com.greenfoxacademy.goddesstribesbackend.repositories;

import com.greenfoxacademy.goddesstribesbackend.models.entities.Townhall;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface TownhallRepository extends CrudRepository<Townhall, Long> {
  ArrayList<Townhall> findAll();
  ArrayList<Townhall> findTownhallsByKingdom_Id(Long kingdomId);
  Optional<Townhall> findById(Long townhallId);
}
