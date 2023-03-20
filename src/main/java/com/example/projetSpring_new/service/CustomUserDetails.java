package com.example.projetSpring_new.service;

import java.util.Collection;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.projetSpring_new.model.User;

public class CustomUserDetails implements UserDetails {

	
	private User user;
	
	
	public CustomUserDetails(User user) {
		
		this.user = user;
	}
	public User getUser() {
		
		User user = new User();
		user.getAdrs();
		user.getEmail();
		user.getId();
		user.getNom();
		
		return user;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}
	public Integer getUserId() {
		return user.getId();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
	
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
	
		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}
	
	public String getFullName() {
		return user.getNom();
	}

}
