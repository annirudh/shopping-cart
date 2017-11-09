package com.delphix.shoppingcart.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.delphix.shoppingcart.models.PurchaseableItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
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
public class PurchaseableItemControllerTest {

  @Autowired
  private ObjectMapper mapper;

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void createAndGetPurchaseableItemTest() throws Exception {
    PurchaseableItem createdItem = createItem("Coffee mug", BigDecimal.TEN);
    assertThat(createdItem.getItemName()).isEqualTo("Coffee mug");
    assertThat(createdItem.getPrice()).isEqualTo(BigDecimal.TEN);

    PurchaseableItem retrievedItem = getItem(createdItem.getId());

    assertThat(createdItem.getId()).isEqualTo(retrievedItem.getId());
    assertThat(createdItem.getItemName()).isEqualTo(retrievedItem.getItemName());
    assertThat(createdItem.getPrice().compareTo(retrievedItem.getPrice())).isEqualTo(0);
  }

  private PurchaseableItem createItem(String itemName, BigDecimal price) throws Exception {
    PurchaseableItem purchaseableItem = new PurchaseableItem(itemName, price);
    String purchaseableItemJson = mapper.writeValueAsString(purchaseableItem);

    String responseJson = mockMvc.perform(
        post("/items")
          .content(purchaseableItemJson)
          .contentType(MediaType.APPLICATION_JSON)
          .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();

    return mapper.readValue(responseJson, PurchaseableItem.class);
  }

  private PurchaseableItem getItem(long id) throws Exception {
    String responseJson = mockMvc.perform(
        get("/items/" + id)
          .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString();

    return mapper.readValue(responseJson, PurchaseableItem.class);
  }
}
