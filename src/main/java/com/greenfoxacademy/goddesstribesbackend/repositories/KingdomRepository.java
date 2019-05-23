package com.greenfoxacademy.goddesstribesbackend.repositories;

import com.greenfoxacademy.goddesstribesbackend.models.entities.Kingdom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KingdomRepository extends CrudRepository<Kingdom, Long> {
  Optional<Kingdom> findKingdomByUser_Username(String username);
}
