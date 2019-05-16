package com.greenfoxacademy.goddesstribesbackend.models.entities;

import javax.persistence.*;

@Entity
public class Kingdom {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String kingdomName;

  @OneToOne(optional = false)
  private User user;

  public Kingdom() {
  }

  public Kingdom(String kingdomName, User user) {
    this.kingdomName = kingdomName;
    this.user = user;
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

}
