package com.example.projetSpring_new.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client {
	private Integer id;
	private String nom_complet;
	private String adresse_complet;
	private String email;
	private String tel;
	public Client() {
		
	}
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNom_complet() {
		return nom_complet;
	}
	public void setNom_complet(String nom_complet) {
		this.nom_complet = nom_complet;
	}
	public String getAdresse_complet() {
		return adresse_complet;
	}
	public void setAdresse_complet(String adresse_complet) {
		this.adresse_complet = adresse_complet;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
	
	
	
}
