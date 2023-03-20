package com.example.projetSpring_new.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.projetSpring_new.model.Payment;
import com.example.projetSpring_new.model.Produit;
import com.example.projetSpring_new.model.User;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	public List<Payment> findByUser(User user);

	public List<Payment> findByProduit(Produit p);

	@Query(nativeQuery = true, value = "SELECT SUM(Payment.qte_paye * Produit.prixu) FROM Payment,Produit WHERE Payment.produit_id=Produit.id ")

	String sumPayement();

	@Query(nativeQuery = true, value = "SELECT SUM(Payment.qte_paye * Produit.prixu) FROM Payment,Produit WHERE Payment.produit_id=Produit.id AND Payment.date like :date ")

	String sumPayementNow(String date);
	
	
	@Query(nativeQuery = true, value = "SELECT * FROM Payment,Produit WHERE Payment.produit_id=Produit.id AND Payment.date like :date ")

	List<Payment> payeNow(String date);
	
	
	@Query(nativeQuery = true, value = "SELECT * FROM Payment p WHERE p.user_id=:user ")
	
	 List<Payment> invoiceByUser(Integer user);
	
	@Query(nativeQuery = true, value = "SELECT COUNT(id) FROM Payment")

	String countPayement();
	
	@Query(nativeQuery = true, value = "SELECT  COUNT(id) FROM Payment WHERE date like :date ")

	String countPayementNow(String date);
	
	
	@Query(nativeQuery = true, value = "SELECT Payment.id,Payment.qte_paye,Payment.date,Payment.produit_id, Payment.user_id,Produit.nom,Produit.prixu FROM Payment,Produit WHERE Payment.produit_id=Produit.id ORDER BY Payment.id DESC LIMIT 3")

	 List<Payment> listTroisPayement();

}
