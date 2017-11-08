package com.delphix.shoppingcart.controllers;

import com.delphix.shoppingcart.models.CreditCard;
import com.delphix.shoppingcart.models.Customer;
import com.delphix.shoppingcart.repositories.CreditCardRepository;
import com.delphix.shoppingcart.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

  @Autowired private CreditCardRepository creditCardRepository;

  @Autowired private CustomerRepository customerRepository;

  @RequestMapping(value = "/customers", method = RequestMethod.GET)
  public Iterable<Customer> getCustomers() {
    return customerRepository.findAll();
  }

  @RequestMapping(value = "/customers", method = RequestMethod.POST)
  public Customer createCustomer(@RequestBody Customer customer) {
    return customerRepository.save(customer);
  }

  @RequestMapping(value = "/customers/{id}", method = RequestMethod.GET)
  public Customer getCustomer(@PathVariable long id) {
    return customerRepository.findOne(id);
  }

  @RequestMapping(value = "/customers/{id}/creditCards", method = RequestMethod.GET)
  public Iterable<CreditCard> getCreditCardsForCustomer(@PathVariable long id) {
    return customerRepository.findOne(id).getCreditCards();
  }

  @RequestMapping(value = "/customers/{id}/creditCards", method = RequestMethod.POST)
  public CreditCard createCreditCardForCustomer(@PathVariable long id, @RequestBody CreditCard card) {
    Customer customer = customerRepository.findOne(id);
    card.setCustomer(customer);

    return creditCardRepository.save(card);
  }


}
