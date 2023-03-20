package com.example.projetSpring_new.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.projetSpring_new.model.Client;
import com.example.projetSpring_new.model.Commande;
import com.example.projetSpring_new.model.Payment;
import com.example.projetSpring_new.model.Produit;
import com.example.projetSpring_new.model.User;
import com.example.projetSpring_new.repository.CommandeRepository;
import com.example.projetSpring_new.repository.PaymentRepository;

@Service
public class CommandeService {
	@Autowired
	private PaymentRepository payeRepo;
	@Autowired
	ProduitService produitService;
	@Autowired
	UserService userService;

	public List<Payment> listPaymenByUsert(User user) {
		return payeRepo.findByUser(user);
	}
	
	
	public List<Payment> invoiceByUser(Integer user) {
		return payeRepo.invoiceByUser(user);
	}

	public Payment save(Payment c) {

		return payeRepo.save(c);
	}

	public List<Payment> listPaymentByproduit(Produit p) {
		return payeRepo.findByProduit(p);
	}
	
	public String sumPayement() {
		return payeRepo.sumPayement(); 
		
	}
	public String countPayement() {
		return payeRepo.countPayement(); 
		
	}
	 
    
	public List<Payment> allPayment(){
		
		return payeRepo.findAll();
	}
	
	public Page<Payment> listAll(int pageNum, String sortField, String sortDir) {

		Pageable pageable = PageRequest.of(pageNum - 1, 10,
				sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());

		return payeRepo.findAll(pageable);
	}
	
	public String sumPayementNow(Date date) {
		date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		String format = formatter.format(date);
		
		return payeRepo.sumPayementNow("%"+format+"%");
		
		 
	}
	public List<Payment> payeNow(Date date){
		date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		String format = formatter.format(date);
		return payeRepo.payeNow("%"+format+"%");
	}
	public String countPayementNow(Date date) {
		date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		String format = formatter.format(date);
		
		return payeRepo.countPayementNow("%"+format+"%");
		
		 
	}
	
	
	public void delete(Integer id) {
		payeRepo.deleteById(id);

	}

	public Payment get(Integer id) {
		return payeRepo.findById(id).get();
	}
}
