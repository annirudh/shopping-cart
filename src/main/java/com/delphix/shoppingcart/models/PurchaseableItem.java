package com.delphix.shoppingcart.models;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PurchaseableItem {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(updatable = false, nullable = false)
  private long id;

  @Column(nullable = false)
  private String itemName;

  @Column(nullable = false)
  private BigDecimal price;

  private PurchaseableItem () { }

  public PurchaseableItem(String itemName, BigDecimal price) {
    this.itemName = itemName;
    this.price = price;
  }

  public long getId() {
    return id;
  }

  public String getItemName() {
    return itemName;
  }

  public BigDecimal getPrice() {
    return price;
  }
}
