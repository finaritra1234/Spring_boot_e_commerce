package com.example.projetSpring_new.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.projetSpring_new.model.Produit;
import com.example.projetSpring_new.repository.ProduitRepository;

@Service
public class ProduitService {
	@Autowired
	private ProduitRepository repo;

	public List<Produit> listAll() {
		return repo.findAll();
	}
	public List<Produit> findByNomLike(String mot) {
		return repo.findByNomLike("%"+mot+"%");
		
	}
	public List<Produit> find(String mot) {
		return repo.findProduit("%"+mot+"%");
		
	}
	public List<Produit> listNouveau() {
		return repo.listNouveaute();
	}
	public List<Produit> listLaptop() {
		return repo.listLaptop();
	}
	public List<Produit> listMobile() {
		return repo.listMobile();
	}
	public List<Produit> listAccessoire() {
		return repo.listAccessoire1();
	}
	public List<Produit> listHp() {
		return repo.listHp();
	}
	public List<Produit> listAcer() {
		return repo.listAcer();
	}
	public List<Produit> listAsus() {
		return repo.listAsus();
	}
	public List<Produit> listSamsung() {
		return repo.listSamsung();
	}
	public List<Produit> listItel() {
		return repo.listItel();
	}
	public List<Produit> listRedmi() {
		return repo.listRedmi();
	}
	public List<Produit> listCasque() {
		return repo.listCasque();
	}
	public List<Produit> listAppareil() {
		return repo.listAppareil();
	}
	public List<Produit> listCherche(String motCle) {
		return repo.listCherche(motCle);
	}
	public Produit save(Produit produit) {
		return repo.save(produit);
	}

	public Produit get(Integer id) {
		return repo.findById(id).get();
	}

	public void delete(Integer id) {
		repo.deleteById(id);
	}

	public Produit updateProduct(Produit p) {
		Produit p1;
		p1 = repo.findById(p.getId()).get();

		p1.setQte(p1.getQte());

		return repo.save(p1);

	}

	public Page<Produit> listAll(int pageNum, String sortField, String sortDir) {

		Pageable pageable = PageRequest.of(pageNum - 1, 8,
				sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());

		return repo.findAll(pageable);
	}
	
	public Page<Produit> listShopAll(int pageNum, String sortField, String sortDir) {

		Pageable pageable = PageRequest.of(pageNum - 1, 10,
				sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());

		return repo.findAll(pageable);
	}
	public List<Produit> listLaptop1(){
		return repo.listLaptop1();
	}
	public List<Produit> listMobile1(){
		return repo.listMobile1();
	}
	public List<Produit> listAccessoire1(){
		return repo.listAccessoire1();
	}

}
