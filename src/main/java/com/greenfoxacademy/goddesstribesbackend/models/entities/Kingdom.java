package com.greenfoxacademy.goddesstribesbackend.models.entities;

import com.greenfoxacademy.goddesstribesbackend.models.Location;

import javax.persistence.*;

@Entity
public class Kingdom {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String kingdomName;

  @OneToOne(optional = false)
  private User user;

  private Location location;

  public Kingdom() {
  }

  public Kingdom(String kingdomName, User user) {
    this.kingdomName = kingdomName;
    this.user = user;
    location = new Location(0, 0);
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

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

}
