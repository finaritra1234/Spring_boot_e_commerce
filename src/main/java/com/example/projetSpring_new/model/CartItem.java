package com.example.projetSpring_new.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "cart_items")
public class CartItem implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "produit_id")
	private Produit produit;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
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
