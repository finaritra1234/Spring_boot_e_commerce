package com.example.projetSpring_new.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "payment")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "produit_id")
	private Produit produit;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	private int qte_paye;
	
	private Date date;
	
	
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
		return qte_paye;
	}

	public void setQte_com(int qte_paye) {
		this.qte_paye = qte_paye;
	}
	
	public int getQte_paye() {
		return qte_paye;
	}

	public void setQte_paye(int qte_paye) {
		this.qte_paye = qte_paye;
	}

	

	public Date getDate() {
		return date;
	}
	/*
	 * public Date getDate() { //SimpleDateFormat formatter = new
	 * SimpleDateFormat();
	 * 
	 * Date date =new Date(); return date; }
	 */

	public void setDate(Date datePaye) {
		this.date = datePaye;
	}

	

	@Transient
	public float getSubtotal() {
		return this.produit.getPrixu() * qte_paye;
	}
	
	
	
}
