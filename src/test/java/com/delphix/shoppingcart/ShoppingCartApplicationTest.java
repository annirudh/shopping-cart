package com.delphix.shoppingcart;

import static com.google.common.truth.Truth.assertThat;

import com.delphix.shoppingcart.controllers.CustomerController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingCartApplicationTest {

	@Autowired
	private CustomerController customerController;

	@Test
	public void contextLoads() {
		assertThat(customerController).isNotNull();
	}
}
