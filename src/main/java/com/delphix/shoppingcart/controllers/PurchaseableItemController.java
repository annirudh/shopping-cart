package com.delphix.shoppingcart.controllers;

import com.delphix.shoppingcart.models.PurchaseableItem;
import com.delphix.shoppingcart.repositories.PurchaseableItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseableItemController {

  @Autowired private PurchaseableItemRepository purchaseableItemRepository;

  @RequestMapping(value = "/items", method = RequestMethod.GET)
  public Iterable<PurchaseableItem> getPurchaseableItems() {
    return purchaseableItemRepository.findAll();
  }

  @RequestMapping(value = "/items", method = RequestMethod.POST)
  public PurchaseableItem createPurchaseableItem(@RequestBody PurchaseableItem purchaseableItem) {
    return purchaseableItemRepository.save(purchaseableItem);
  }

  @RequestMapping(value = "/items/{id}", method = RequestMethod.GET)
  public PurchaseableItem getPurchaseableItem(@PathVariable long id) {
    return purchaseableItemRepository.findOne(id);
  }
}
