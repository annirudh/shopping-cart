package com.delphix.shoppingcart;

import com.delphix.shoppingcart.models.CreditCard;
import com.delphix.shoppingcart.models.CreditCard.CardType;
import com.delphix.shoppingcart.models.Customer;
import com.delphix.shoppingcart.models.PurchaseableItem;
import com.delphix.shoppingcart.repositories.CreditCardRepository;
import com.delphix.shoppingcart.repositories.CustomerRepository;
import com.delphix.shoppingcart.repositories.PurchaseableItemRepository;
import com.google.common.collect.Lists;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class StackStartupDatabaseInitializer implements ApplicationListener<ContextRefreshedEvent> {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private CreditCardRepository creditCardRepository;

  @Autowired
  private PurchaseableItemRepository purchaseableItemRepository;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    Customer jane = new Customer("jane@yahoo.com");
    CreditCard janeMasterCard = new CreditCard(CardType.MASTERCARD, "111", jane);
    CreditCard janeVisa = new CreditCard(CardType.VISA, "222", jane);

    Customer joe  = new Customer("joe@gmail.com");
    CreditCard joeMasterCard = new CreditCard(CardType.MASTERCARD, "000", joe);

    PurchaseableItem macbookPro = new PurchaseableItem("MacBook Pro", BigDecimal.valueOf(1899d));
    PurchaseableItem microsoftSurface = new PurchaseableItem("Microsoft Surface", BigDecimal.valueOf(799.99));

    customerRepository.save(Lists.newArrayList(jane, joe));
    creditCardRepository.save(Lists.newArrayList(janeMasterCard, janeVisa, joeMasterCard));
    purchaseableItemRepository.save(Lists.newArrayList(macbookPro, microsoftSurface));
  }
}
