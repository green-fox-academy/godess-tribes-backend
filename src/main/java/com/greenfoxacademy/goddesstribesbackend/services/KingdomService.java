package com.greenfoxacademy.goddesstribesbackend.services;

import com.greenfoxacademy.goddesstribesbackend.models.entities.Kingdom;
import com.greenfoxacademy.goddesstribesbackend.models.entities.User;
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

  public Kingdom saveKingdom(String kingdomname, User user) {
    if (user != null && userService.checkUserByName(user.getUsername())) {
      Kingdom newKingdom;

      if (kingdomname == null || kingdomname.isEmpty()) {
        newKingdom = new Kingdom(user.getUsername() + "'s kingdom", user);
      } else {
        newKingdom = new Kingdom(kingdomname, user);
      }
      return kingdomRepository.save(newKingdom);
    }
    return null;
  }

}
