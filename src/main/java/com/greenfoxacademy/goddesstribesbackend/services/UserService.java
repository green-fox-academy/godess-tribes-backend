package com.greenfoxacademy.goddesstribesbackend.services;

import com.greenfoxacademy.goddesstribesbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private UserRepository userRepository;
  private KingdomService kingdomService;

  @Autowired
  public UserService(UserRepository userRepository, KingdomService kingdomService) {
    this.userRepository = userRepository;
    this.kingdomService = kingdomService;
  }

}
