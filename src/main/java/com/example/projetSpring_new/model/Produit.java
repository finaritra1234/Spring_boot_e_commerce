package com.example.projetSpring_new.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produit {
	private Integer id;
	private String ref_produit;
	private String nom;
	private String date_creation;
	private float prixu;
	private Integer qte;
	private String image;
	private String categorie;
	public Produit() {
		
	}
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRef_produit() {
		return ref_produit;
	}
	public void setRef_produit(String ref_produit) {
		this.ref_produit = ref_produit;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDate_creation() {
		return date_creation;
	}
	public void setDate_creation(String date_creation) {
		this.date_creation = date_creation;
	}
	public float getPrixu() {
		return prixu;
	}
	public void setPrixu(float prixu) {
		this.prixu = prixu;
	}
	public Integer getQte() {
		return qte;
	}
	public void setQte(Integer qte) {
		this.qte = qte;
	}
	
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
    
	
	
	
	
	
}
