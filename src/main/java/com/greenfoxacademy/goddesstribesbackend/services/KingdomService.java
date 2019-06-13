package com.greenfoxacademy.goddesstribesbackend.services;

import com.greenfoxacademy.goddesstribesbackend.models.dtos.AuthenticationResponseDTO;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.KingdomDTO;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.LocationDTO;
import com.greenfoxacademy.goddesstribesbackend.models.dtos.TokenDTO;
import com.greenfoxacademy.goddesstribesbackend.models.entities.Kingdom;
import com.greenfoxacademy.goddesstribesbackend.models.entities.Townhall;
import com.greenfoxacademy.goddesstribesbackend.models.entities.User;
import com.greenfoxacademy.goddesstribesbackend.repositories.KingdomRepository;
import com.greenfoxacademy.goddesstribesbackend.security.jwt.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KingdomService {

  private KingdomRepository kingdomRepository;
  private UserService userService;
  private BuildingService buildingService;
  private ResourceService resourceService;
  private SoldierService soldierService;
  private ProductionService productionService;

  @Autowired
  public KingdomService(KingdomRepository kingdomRepository, UserService userService,
                        BuildingService buildingService, ResourceService resourceService,
                        SoldierService soldierService, ProductionService productionService) {
    this.kingdomRepository = kingdomRepository;
    this.userService = userService;
    this.buildingService = buildingService;
    this.resourceService = resourceService;
    this.soldierService = soldierService;
    this.productionService = productionService;
  }

  public Kingdom findKingdomById(Long kingdomId) {
    return kingdomRepository.findById(kingdomId).orElse(null);
  }

  public Kingdom findKingdomByUsername(String username) {
    return kingdomRepository.findKingdomByUser_Username(username).orElse(null);
  }

  public Kingdom saveKingdom(String kingdomName, User user) {
    if (user != null && userService.checkUserByName(user.getUsername())) {
      Kingdom newKingdom;

      if (kingdomName == null || kingdomName.isEmpty()) {
        kingdomName = user.getUsername() + "'s kingdom";
      }
      return kingdomRepository.save(new Kingdom(kingdomName, user));
    }
    return null;
  }

  public void initKingdom(String username) {
    if (username != null && userService.checkUserByName(username)) {
      Kingdom kingdom = kingdomRepository.findKingdomByUser_Username(username).get();

      if (!kingdom.isActive()) {
        Townhall townhall = buildingService.saveTownhall(kingdom);
        resourceService.saveFoodAtStart(townhall);
        resourceService.saveGoldAtStart(townhall);
        buildingService.saveFarmAtStart(kingdom);
        buildingService.saveMineAtStart(kingdom);
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

  public KingdomDTO createKingdomDTO(Kingdom kingdom) {
    KingdomDTO kingdomDTO = new KingdomDTO();

    kingdomDTO.setId(kingdom.getId());
    kingdomDTO.setKingdomName(kingdom.getKingdomName());
    kingdomDTO.setUserId(kingdom.getUser().getId());
    kingdomDTO.setBuildings(buildingService.createBuildingsDTO(kingdom.getUser().getUsername()).getBuildings());
    kingdomDTO.setResources(productionService.createResourcesDTO(kingdom.getId()).getResources());
    kingdomDTO.setSoldiers(soldierService.createSoldiersDTO(kingdom.getId()).getSoldiers());
    kingdomDTO.setLocation(new LocationDTO(kingdom.getxCoord(), kingdom.getyCoord()));

    return kingdomDTO;
  }

  public Kingdom renameKingdom(String name, Kingdom kingdom) {
    kingdom.setKingdomName(name);
    return kingdomRepository.save(kingdom);
  }

}
