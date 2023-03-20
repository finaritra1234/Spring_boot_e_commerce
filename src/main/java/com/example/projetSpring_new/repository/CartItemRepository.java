package com.example.projetSpring_new.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.projetSpring_new.model.CartItem;
import com.example.projetSpring_new.model.Produit;
import com.example.projetSpring_new.model.User;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
	public List<CartItem> findByUser(User user);
	public List<CartItem> findByProduit(Produit p);

	@Query("DELETE CartItem c WHERE c.user=:user") 
	 
	 @Modifying
	 void deleteByUser(User user);
}
