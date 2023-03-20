package com.example.projetSpring_new.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projetSpring_new.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
