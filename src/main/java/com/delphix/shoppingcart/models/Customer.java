package com.delphix.shoppingcart.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(updatable = false, nullable = false)
  private long id;

  @Column(unique = true, nullable = false)
  private String email;

  @JsonIgnore
  @OneToMany(mappedBy = "customer")
  private Set<CreditCard> creditCards;

  private Customer() { }

  public Customer(String email) {
    this.email = email;
  }

  public long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public Set<CreditCard> getCreditCards() {
    return creditCards;
  }
}
