package com.greenfoxacademy.goddesstribesbackend.repositories;

import com.greenfoxacademy.goddesstribesbackend.modells.Kingdom;
import org.springframework.data.repository.CrudRepository;

public interface KingdomRepository extends CrudRepository<Kingdom, Long> {
}