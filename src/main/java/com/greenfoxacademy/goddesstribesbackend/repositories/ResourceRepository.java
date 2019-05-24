package com.greenfoxacademy.goddesstribesbackend.repositories;

import com.greenfoxacademy.goddesstribesbackend.models.ResourceTypeENUM;
import com.greenfoxacademy.goddesstribesbackend.models.entities.Resource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface ResourceRepository extends CrudRepository<Resource, Long> {
  ArrayList<Resource> findResourcesByTownhall_Kingdom_Id(Long kingdomId);
  Optional<Resource> findResourceByTownhall_Kingdom_IdAndType(Long kingdomId, ResourceTypeENUM type);
}
