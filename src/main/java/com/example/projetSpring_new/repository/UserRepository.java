package com.example.projetSpring_new.repository;

//import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.projetSpring_new.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	  @Query("SELECT u FROM User u WHERE u.email = ?1") User findByEmail(String
	  email);
	  
	  @Query("Update User u set u.ville=:ville, u.adrs=:adrs, u.cp=:cp,u.tel=:tel  WHERE u.id =:id")

		@Modifying
		void update(String ville, String adrs, String cp, String tel,Integer id);
	 
	
	  //Optional<User> findUserByEmail(String email);
}
