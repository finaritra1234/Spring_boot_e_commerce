package com.example.projetSpring_new.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.projetSpring_new.model.Client;
import com.example.projetSpring_new.model.Commande;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Integer> {

	 public List<Commande> findByClient(Client client);
	
}
