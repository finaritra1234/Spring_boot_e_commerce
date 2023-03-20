package com.example.projetSpring_new.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name = "payment")
public class PaymentDto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private int produit_id;
	private int user_id;
	private int qte_paye;
	private String ville;
	private String adresse;
	private String cp;
	private String tel;
	private Date date;
	private String status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getProduit_id() {
		return produit_id;
	}

	public void setProduit_id(int produit_id) {
		this.produit_id = produit_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
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

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
