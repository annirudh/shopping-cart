package com.delphix.shoppingcart.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", updatable = false, nullable = false)
  private long id;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private int creditCardNumber;

  private User() { }

  public User(String email, int creditCardNumber) {
    this.email = email;
    this.creditCardNumber = creditCardNumber;
  }

  public long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public int getCreditCardNumber() {
    return creditCardNumber;
  }
}
