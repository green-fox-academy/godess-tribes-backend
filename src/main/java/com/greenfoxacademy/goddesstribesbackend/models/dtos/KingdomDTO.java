package com.greenfoxacademy.goddesstribesbackend.models.dtos;

import java.util.List;

public class KingdomDTO {

  private Long id;
  private String kingdomName;
  private Long userId;
  private List<BuildingDTO> buildings;
  private List<ResourceDTO> resources;
  private List<SoldierDTO> soldiers;
  private LocationDTO location;

  public KingdomDTO() {
  }

  public KingdomDTO(Long id, String kingdomName, Long userId,
                    List<BuildingDTO> buildings, List<ResourceDTO> resources,
                    List<SoldierDTO> soldiers, LocationDTO location) {
    this.id = id;
    this.kingdomName = kingdomName;
    this.userId = userId;
    this.buildings = buildings;
    this.resources = resources;
    this.soldiers = soldiers;
    this.location = location;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getKingdomName() {
    return kingdomName;
  }

  public void setKingdomName(String kingdomName) {
    this.kingdomName = kingdomName;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public List<BuildingDTO> getBuildings() {
    return buildings;
  }

  public void setBuildings(List<BuildingDTO> buildings) {
    this.buildings = buildings;
  }

  public List<ResourceDTO> getResources() {
    return resources;
  }

  public void setResources(List<ResourceDTO> resources) {
    this.resources = resources;
  }

  public List<SoldierDTO> getSoldiers() {
    return soldiers;
  }

  public void setSoldiers(List<SoldierDTO> soldiers) {
    this.soldiers = soldiers;
  }

  public LocationDTO getLocation() {
    return location;
  }

  public void setLocation(LocationDTO location) {
    this.location = location;
  }

}
