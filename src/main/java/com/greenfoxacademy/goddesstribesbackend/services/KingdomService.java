package com.greenfoxacademy.goddesstribesbackend.services;

import com.greenfoxacademy.goddesstribesbackend.models.dtos.TokenDTO;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.AuthenticationResponseDTO;
import com.greenfoxacademy.goddesstribesbackend.models.entities.Kingdom;
import com.greenfoxacademy.goddesstribesbackend.models.entities.User;
import com.greenfoxacademy.goddesstribesbackend.repositories.KingdomRepository;
import com.greenfoxacademy.goddesstribesbackend.security.jwt.JWTUtility;
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

  public Kingdom saveKingdom(String kingdomName, User user) {
    if (user != null && userService.checkUserByName(user.getUsername())) {
      Kingdom newKingdom;

      if (kingdomName == null || kingdomName.isEmpty()) {
        newKingdom = new Kingdom(user.getUsername() + "'s kingdom", user);
      } else {
        newKingdom = new Kingdom(kingdomName, user);
      }
      return kingdomRepository.save(newKingdom);
    }
    return null;
  }

  public void initKingdom(String username) {
    if (username != null && userService.checkUserByName(username)) {
      Kingdom kingdom = kingdomRepository.findKingdomByUser_Username(username).get();

      if (!kingdom.isActive()) {
        //create a townhall with some gold and food
        //create a farm and a mine
        kingdom.setActive(true);
        kingdomRepository.save(kingdom);
      }
    }
  }

  public AuthenticationResponseDTO createAuthenticationResponseDTO(TokenDTO tokenDTO) {
    if (tokenDTO == null) return null;
    String username = JWTUtility.parseToken(tokenDTO.getToken());

    if (username != null && userService.checkUserByName(username)) {
      User user = userService.findUserByName(username).get();
      Kingdom kingdom = kingdomRepository.findKingdomByUser_Username(username).get();
      return new AuthenticationResponseDTO(user.getId(), kingdom.getId());
    }
    return null;
  }

}
