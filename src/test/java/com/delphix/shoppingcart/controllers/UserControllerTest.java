package com.delphix.shoppingcart.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.delphix.shoppingcart.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

  @Autowired
  private ObjectMapper mapper;

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void createAndGetUserTest() throws Exception {
    User user = new User("test@testmail.com", 000);
    String userJson = mapper.writeValueAsString(user);

    // Create the desired user.
    String responseJson = mockMvc.perform(
        post("/users")
          .content(userJson)
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();

    User createdUser = mapper.readValue(responseJson, User.class);

    mockMvc.perform(
        get("/users/" + createdUser.getId())
          .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(responseJson));
  }
}
