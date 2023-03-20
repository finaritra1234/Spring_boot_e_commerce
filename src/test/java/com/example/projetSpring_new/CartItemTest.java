package com.example.projetSpring_new;





import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.example.projetSpring_new.model.CartItem;

import com.example.projetSpring_new.model.Produit;
import com.example.projetSpring_new.model.User;
import com.example.projetSpring_new.repository.CartItemRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CartItemTest {
	@Autowired
	private CartItemRepository comRepo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testAddOneCartItems() {
		Produit produit = entityManager.find(Produit.class, 1);
		User user = entityManager.find(User.class, 1);
		
		CartItem newCom = new CartItem();
		newCom.setProduit(produit);
		newCom.setUser(user);
		newCom.setQte_com(1);
		
		CartItem savedCom = comRepo.save(newCom);
		
		assertTrue(savedCom.getId() > 0);
		
		
		
	}
	@Test
	public void testGetComByUser() {
		
		User user = new User();
		user.setId(1);
		
		List<CartItem> l = comRepo.findByUser(user);
		
		assertEquals(2, l.size());
		
		
		
	}
}
