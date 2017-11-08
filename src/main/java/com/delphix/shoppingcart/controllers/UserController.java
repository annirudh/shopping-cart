package com.delphix.shoppingcart.controllers;

import com.delphix.shoppingcart.models.User;
import com.delphix.shoppingcart.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired private UserRepository userRepository;

  @RequestMapping(value = "/users", method = RequestMethod.GET)
  public Iterable<User> getUsers() {
    return userRepository.findAll();
  }

  @RequestMapping(value = "/users", method = RequestMethod.POST)
  public User createUser(@RequestBody User user) {
    return userRepository.save(user);
  }

  @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
  public User getUser(@PathVariable long id) {
    return userRepository.findOne(id);
  }
}
