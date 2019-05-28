package com.greenfoxacademy.goddesstribesbackend.models.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Kingdom {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String kingdomName;

  private int xCoord;
  private int yCoord;
  private boolean active;

  @OneToOne(optional = false)
  private User user;

  @OneToMany(mappedBy = "kingdom" , cascade = CascadeType.PERSIST)
  private List<Building> buildings;

  public Kingdom() {
  }

  public Kingdom(String kingdomName, User user) {
    this.kingdomName = kingdomName;
    xCoord = 0;
    yCoord = 0;
    active = false;
    this.user = user;
    buildings = new ArrayList<>();
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

  public int getxCoord() {
    return xCoord;
  }

  public void setxCoord(int xCoord) {
    this.xCoord = xCoord;
  }

  public int getyCoord() {
    return yCoord;
  }

  public void setyCoord(int yCoord) {
    this.yCoord = yCoord;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<Building> getBuildings() {
    return buildings;
  }

  public void setBuildings(List<Building> buildings) {
    this.buildings = buildings;
  }

}
