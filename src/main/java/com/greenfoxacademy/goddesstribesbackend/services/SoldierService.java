package com.greenfoxacademy.goddesstribesbackend.services;

import com.greenfoxacademy.goddesstribesbackend.repositories.SoldierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SoldierService {

  private SoldierRepository soldierRepository;

  @Autowired
  public SoldierService(SoldierRepository soldierRepository) {
    this.soldierRepository = soldierRepository;
  }

}
