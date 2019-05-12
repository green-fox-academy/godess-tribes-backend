package com.greenfoxacademy.goddesstribesbackend.services;

import com.greenfoxacademy.goddesstribesbackend.repositories.KingdomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KingdomService {

  private KingdomRepository kingdomRepository;
  private UserService userService;

  @Autowired
  public KingdomService(KingdomRepository kingdomRepository, UserService userService) {
    this.kingdomRepository = kingdomRepository;
    this.userService = userService;
  }

}
