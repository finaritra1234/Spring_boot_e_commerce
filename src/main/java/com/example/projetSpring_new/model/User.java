package com.example.projetSpring_new.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, unique = true, length = 45)

	private String email;
	
	@Column(nullable = false, length = 45)
	private String nom;
	
	@Column(nullable = false, length = 45)
	private String adrs;
	
	
	private String ville;
	private String cp;
	private String tel;
	
	@Column(nullable = false, length = 45)
	private String password;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNom() {
		return nom;
	}
	
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat();
		
		Date date =new Date();
		return formatter.format(date);
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getAdrs() {
		return adrs;
	}
	public void setAdrs(String adrs) {
		this.adrs = adrs;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public User(User user) {
		this.adrs = user.getAdrs();
		this.email= user.getEmail();
		this.nom = user.getNom();
		this.password = user.getPassword();
	}
	public User() {
		
	}
	
}
