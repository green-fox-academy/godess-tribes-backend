package com.greenfoxacademy.goddesstribesbackend.controllers;

import com.greenfoxacademy.goddesstribesbackend.services.KingdomService;
import com.greenfoxacademy.goddesstribesbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private UserService userService;
  private KingdomService kingdomService;

  @Autowired
  public UserController(UserService userService, KingdomService kingdomService) {
    this.userService = userService;
    this.kingdomService = kingdomService;
  }

}
