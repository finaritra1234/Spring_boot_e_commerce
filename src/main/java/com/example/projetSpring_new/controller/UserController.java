package com.example.projetSpring_new.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.projetSpring_new.model.User;
import com.example.projetSpring_new.repository.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository repo;
	
	@GetMapping("/user")
	public String viewUserPage() {
		return "user";
	}
	
	@GetMapping("/registera")
	public String showSignupForm(Model model) {
		model.addAttribute("user", new User());
		
		return "user_form";
	}
	@PostMapping("/process_register")
	public String processRegistration(User user) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodePassword = encoder.encode(user.getPassword());
		user.setPassword(encodePassword);
		
		repo.save(user);
		
		return "register_success";
	}
	@GetMapping("/list_users")
	public String viewUserList() {
		
		
		return "user_list";
	}
	
	@RequestMapping("/logina")
	public String login() {
		return "login.html";
	}
	@RequestMapping("/login-errofr")
	public String loginError(Model model) {
		model.addAttribute("loginError",true);
		return "login.html";
	}
}
