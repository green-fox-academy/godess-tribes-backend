package com.greenfoxacademy.goddesstribesbackend.repositories;

import com.greenfoxacademy.goddesstribesbackend.models.entities.Barrack;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BarrackRepository extends CrudRepository<Barrack, Long> {

  ArrayList<Barrack> findAll();

  ArrayList<Barrack> findBarracksByKingdom_Id(Long id);

}
