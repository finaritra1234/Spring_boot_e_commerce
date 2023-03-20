package com.example.projetSpring_new.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.projetSpring_new.model.Produit;
import com.example.projetSpring_new.model.User;

public interface ProduitRepository extends JpaRepository<Produit, Integer> {

	/*
	 * @Modifying
	 * 
	 * @Query("update Produit p set p.qte=:qte  where p.id=:id") void
	 * updateById(Integer id,Integer qte);
	 */
	List<Produit> findByNomLike(String mot);
	@Query(nativeQuery = true , value = "SELECT * FROM Produit WHERE nom LIKE '%mot%'") List<Produit>
	  listCherche(String mot);
	
	
	
	@Query(nativeQuery = true , value = "SELECT * FROM Produit WHERE id LIKE :mot or nom LIKE :mot or categorie LIKE :mot") List<Produit>
	  findProduit(String mot);
	
	
	@Query(nativeQuery = true , value = "SELECT * FROM Produit ORDER BY id DESC LIMIT 3 ") List<Produit>
	  listNouveaute();
	
	
	@Query(nativeQuery = true , value = "SELECT * FROM Produit ORDER BY prixu DESC LIMIT 3 ") List<Produit>
	  listLaptop();
	
	
	
	@Query(nativeQuery = true , value = "SELECT * FROM Produit WHERE categorie='Mobile' ORDER BY prixu DESC LIMIT 3 ") List<Produit>
	  listMobile();
	
	
	@Query(nativeQuery = true , value = "SELECT * FROM Produit WHERE categorie='Laptop'") List<Produit>
	  listLaptop1();
	
	
	
	@Query(nativeQuery = true , value = "SELECT * FROM Produit WHERE categorie='Mobile'") List<Produit>
	  listMobile1();
	
	@Query(nativeQuery = true , value = "SELECT * FROM Produit WHERE categorie='Accesoire'") List<Produit>
	  listAccessoire1();
	
	@Query(nativeQuery = true , value = "SELECT * FROM Produit WHERE nom like '%Hp%'") List<Produit>
	  listHp();
	@Query(nativeQuery = true , value = "SELECT * FROM Produit WHERE nom like '%Acer%'") List<Produit>
	  listAcer();
	@Query(nativeQuery = true , value = "SELECT * FROM Produit WHERE nom like '%Asus%'") List<Produit>
	  listAsus();
	@Query(nativeQuery = true , value = "SELECT * FROM Produit WHERE nom like '%Samsung%'") List<Produit>
	  listSamsung();
	@Query(nativeQuery = true , value = "SELECT * FROM Produit WHERE nom like '%Itel%'") List<Produit>
	  listItel();
	@Query(nativeQuery = true , value = "SELECT * FROM Produit WHERE nom like '%Redmi%'") List<Produit>
	  listRedmi();
	@Query(nativeQuery = true , value = "SELECT * FROM Produit WHERE nom like '%Casque%'") List<Produit>
	  listCasque();
	@Query(nativeQuery = true , value = "SELECT * FROM Produit WHERE nom like '%Appareil%'") List<Produit>
	  listAppareil();
	
	
	
	
	
	
}
