package com.travelport.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public class User {

  @Id private Integer id;

  @Column(name = "character")
  private String character;

  private String house;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getCharacter() {
    return character;
  }

  public void setCharacter(String character) {
    this.character = character;
  }

  public String getHouse() {
    return house;
  }

  public void setHouse(String house) {
    this.house = house;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User user)) return false;
    return Objects.equals(id, user.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
