package com.greenfoxacademy.goddesstribesbackend.repositories;

import com.greenfoxacademy.goddesstribesbackend.models.entities.Resource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ResourceRepository extends CrudRepository<Resource, Long> {
  ArrayList<Resource> findResourcesByTownhall_Kingdom_Id(Long kingdomId);
}
