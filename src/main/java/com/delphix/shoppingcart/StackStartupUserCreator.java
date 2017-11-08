package com.delphix.shoppingcart;

import com.delphix.shoppingcart.models.User;
import com.delphix.shoppingcart.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class StackStartupUserCreator implements ApplicationListener<ContextRefreshedEvent> {

  @Autowired
  private UserRepository userRepository;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    userRepository.save(new User("jane@yahoo.com", 123));
    userRepository.save(new User("joe@gmail.com", 456));
  }
}
