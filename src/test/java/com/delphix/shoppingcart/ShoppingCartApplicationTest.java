package com.delphix.shoppingcart;

import static com.google.common.truth.Truth.assertThat;

import com.delphix.shoppingcart.controllers.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShoppingCartApplicationTest {

	@Autowired
	private UserController userController;

	@Test
	public void contextLoads() {
		assertThat(userController).isNotNull();
	}
}
