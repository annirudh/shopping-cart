package com.delphix.shoppingcart.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CreditCard {

  public enum CardType {
    VISA,
    MASTERCARD
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(updatable = false, nullable = false)
  private long id;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private CardType cardType;

  @Column(unique = true, nullable = false, length = 19)
  private String cardNumber;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "customerId", nullable = false)
  private Customer customer;

  private CreditCard() { }

  public CreditCard(CardType cardType, String cardNumber, Customer customer) {
    this.cardType = cardType;
    this.cardNumber = cardNumber;
    this.customer = customer;
  }

  public long getId() {
    return id;
  }

  public CardType getCardType() {
    return cardType;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    CreditCard that = (CreditCard) o;

    if (id != that.id) {
      return false;
    }
    if (cardType != that.cardType) {
      return false;
    }
    return cardNumber != null ? cardNumber.equals(that.cardNumber) : that.cardNumber == null;
  }

  @Override
  public int hashCode() {
    int result = (int) (id ^ (id >>> 32));
    result = 31 * result + (cardType != null ? cardType.hashCode() : 0);
    result = 31 * result + (cardNumber != null ? cardNumber.hashCode() : 0);
    return result;
  }
}
