package com.delphix.shoppingcart.repositories;

import com.delphix.shoppingcart.models.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
