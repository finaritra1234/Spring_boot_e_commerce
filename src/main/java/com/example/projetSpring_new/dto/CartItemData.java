package com.example.projetSpring_new.dto;

import java.io.Serializable;

import org.springframework.data.annotation.Transient;

import com.example.projetSpring_new.model.Produit;
import com.example.projetSpring_new.model.User;


public class CartItemData  implements Serializable{
	
	private Integer id;
	

	private Produit produit;
	

	private User user;
	
	private int qte_com;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	


	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getQte_com() {
		return qte_com;
	}

	public void setQte_com(int qte_com) {
		this.qte_com = qte_com;
	}
	
	@Transient
	public float getSubtotal() {
		return this.produit.getPrixu() * qte_com;
	}
	
	
	
}
