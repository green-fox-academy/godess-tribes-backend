package com.greenfoxacademy.goddesstribesbackend.repositories;

import com.greenfoxacademy.goddesstribesbackend.models.entities.Kingdom;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface KingdomRepository extends CrudRepository<Kingdom, Long> {
  Optional<Kingdom> findKingdomByUser_Username(String username);
}
