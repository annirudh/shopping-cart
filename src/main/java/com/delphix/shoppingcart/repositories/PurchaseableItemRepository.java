package com.delphix.shoppingcart.repositories;

import com.delphix.shoppingcart.models.PurchaseableItem;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseableItemRepository extends CrudRepository<PurchaseableItem, Long> {

}
