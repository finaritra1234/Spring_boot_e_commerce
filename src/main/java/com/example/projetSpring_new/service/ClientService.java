package com.example.projetSpring_new.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projetSpring_new.model.Client;

import com.example.projetSpring_new.repository.ClientRepository;


@Service
public class ClientService {
	@Autowired
	private ClientRepository repo;
	
	public List<Client> listAll(){
		return repo.findAll();
	}
	
	public Client save(Client produit) {
		return repo.save(produit);
	}
	
	public Client get(Integer id) {
		return repo.findById(id).get();
	}
	
	public void delete(Integer id){
		repo.deleteById(id);
	}
}
