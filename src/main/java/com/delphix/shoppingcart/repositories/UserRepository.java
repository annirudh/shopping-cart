package com.delphix.shoppingcart.repositories;

import com.delphix.shoppingcart.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
