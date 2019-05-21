package com.greenfoxacademy.goddesstribesbackend.repositories;

import com.greenfoxacademy.goddesstribesbackend.models.entities.Resource;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ResourceRepository extends CrudRepository<Resource, Long> {
  ArrayList<Resource> findResourcesByTownhall_Kingdom_Id(Long kingdomId);
}
