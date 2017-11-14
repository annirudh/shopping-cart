package com.delphix.shoppingcart.controllers;

import static com.google.common.truth.Truth.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.delphix.shoppingcart.models.CreditCard;
import com.delphix.shoppingcart.models.CreditCard.CardType;
import com.delphix.shoppingcart.models.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
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
public class CustomerControllerTest {

  @Autowired
  private ObjectMapper mapper;

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void createAndGetUserTest() throws Exception {
    Customer createdCustomer = createCustomer("test@testmail.com");
    assertThat(createdCustomer.getEmail()).isEqualTo("test@testmail.com");

    Customer retrievedCustomer = getCustomer(createdCustomer.getId());

    assertThat(createdCustomer.getId()).isEqualTo(retrievedCustomer.getId());
    assertThat(createdCustomer.getEmail()).isEqualTo(retrievedCustomer.getEmail());
  }

  @Test
  public void createAndGetCreditCardForCustomerTest() throws Exception {
    // TODO: Write test for adding credit card.
  }

  private Customer createCustomer(String email) throws Exception {
    Customer customer = new Customer(email);
    String userJson = mapper.writeValueAsString(customer);

    String responseJson = mockMvc.perform(
        post("/customers")
            .content(userJson)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();

    return mapper.readValue(responseJson, Customer.class);
  }

  private Customer getCustomer(long id) throws Exception {
    String responseJson = mockMvc.perform(
        get("/customers/" + id)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();

    return mapper.readValue(responseJson, Customer.class);
  }

  private CreditCard createCreditCardForCustomer(Customer customer, CardType cardType, String cardNumber)
      throws Exception {
    CreditCard creditCard = new CreditCard(cardType, cardNumber, customer);
    String creditCardJson = mapper.writeValueAsString(creditCard);

    String responseJson = mockMvc.perform(
        post("/customers/" + customer.getId() + "/creditCards")
            .content(creditCardJson)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();

    return mapper.readValue(responseJson, CreditCard.class);
  }

  private List<CreditCard> getCreditCardsForCustomer(Customer customer) throws Exception {
    String responseJson = mockMvc.perform(
        get("/customers/" + customer.getId() + "/creditCards")
          .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();

    return Arrays.asList(mapper.readValue(responseJson, CreditCard[].class));
  }
}
