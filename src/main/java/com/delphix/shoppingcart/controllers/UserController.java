package com.delphix.shoppingcart.controllers;

import com.delphix.shoppingcart.models.User;
import com.delphix.shoppingcart.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired private UserRepository userRepository;

  @RequestMapping(value = "/users", method = RequestMethod.GET)
  public @ResponseBody Iterable<User> getUsers() {
    return userRepository.findAll();
  }
}
