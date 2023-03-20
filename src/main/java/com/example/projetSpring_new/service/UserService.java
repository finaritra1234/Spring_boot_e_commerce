package com.example.projetSpring_new.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.projetSpring_new.model.Produit;
import com.example.projetSpring_new.model.User;
import com.example.projetSpring_new.repository.ProduitRepository;
import com.example.projetSpring_new.repository.UserRepository;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository repo;
	
	
	public User get(Integer id) {
		return repo.findById(id).get();
	}
	public List<User> list() {
		return repo.findAll();
	}
	
	public User update(User u) {
		User u1 ;
		u1 = repo.findById(u.getId()).get();
		u1.setId(u1.getId());
		u1.setAdrs(u1.getAdrs());
		u1.setVille(u1.getVille());
		u1.setCp(u1.getCp());
		u1.setTel(u1.getTel());
		
		
		return repo.save(u1);
		
	}
	public void update(String ville, String adrs, String cp, String tel,Integer id ) {
		repo.update(ville, adrs, cp, tel, id);
	}

}
