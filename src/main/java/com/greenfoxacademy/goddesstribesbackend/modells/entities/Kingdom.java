package com.greenfoxacademy.goddesstribesbackend.modells.entities;

import javax.persistence.*;

@Entity
public class Kingdom {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String kingdomname;

  @OneToOne(optional = false)
  private User user;

  public Kingdom() {
  }

  public Kingdom(String kingdomname, User user) {
    this.kingdomname = kingdomname;
    this.user = user;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getKingdomname() {
    return kingdomname;
  }

  public void setKingdomname(String kingdomname) {
    this.kingdomname = kingdomname;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

}
