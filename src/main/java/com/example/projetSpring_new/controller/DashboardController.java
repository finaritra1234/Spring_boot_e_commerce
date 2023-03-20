package com.example.projetSpring_new.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.projetSpring_new.model.Payment;
import com.example.projetSpring_new.model.Produit;
import com.example.projetSpring_new.model.User;
import com.example.projetSpring_new.repository.PaymentRepository;
import com.example.projetSpring_new.service.PaymentService;
import com.example.projetSpring_new.service.ProduitService;
import com.example.projetSpring_new.service.UserService;

@Controller
public class DashboardController {
	@Autowired
	PaymentService pService;
	@Autowired
	UserService uService;
	@Autowired
	ProduitService prService;
	@Autowired
	PaymentRepository pRepo;
	@RequestMapping("/dashboard")
	public String viewDashoard(Model model) {
		
		// List<Payment> page = pService.allPayment();
		
		// model.addAttribute("listPayes", page);
		 return viewPagePayment(model, 1, "id", "asc");
	}
	@RequestMapping("/dashboard/page/{numPage}")
	public String viewPagePayment(Model model, @PathVariable(name = "numPage") int pageNum,
			@Param("sortField") String sortField, @Param("sortDir") String sortDir) {

		Page<Payment> page = pService.listAll(pageNum, sortField, sortDir);

		List<Payment> listPaye = page.getContent();
		List<Payment> p =  pRepo.listTroisPayement();
		Date date= new Date();

		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		 model.addAttribute("listPayes", p);
		 
		 model.addAttribute("sumPayement",pService.sumPayement());
		 model.addAttribute("sumPayementNow",pService.sumPayementNow(date));
		 model.addAttribute("countPayement",pService.countPayement());
		 model.addAttribute("countPayementNow",pService.countPayementNow(date));

		return "dashboard";
	}
	
	@RequestMapping("/editCom/{id}")
	
	public ModelAndView showEditComtForm(@PathVariable(name = "id") Integer id, Model model) {
		
		ModelAndView mav = new ModelAndView("edit_com");
		
		Payment paye = pService.get(id);
		List<User> user = uService.list();
		List<Produit> produit = prService.listAll();
		
		mav.addObject("payments", paye);
		model.addAttribute("users",user);
		model.addAttribute("produits",produit);
		return mav;
	}
	@RequestMapping("/deleteCom/{id}")
	public String deleteCom(@PathVariable(name = "id") Integer id, Model model, RedirectAttributes rm) {
		pService.delete(id);
		rm.addFlashAttribute("mety","Commande supprimer avec success");
		return "redirect:/dashboard";
	}
	@PostMapping("/com/add")
	public String saveProduits(@ModelAttribute("payments") Payment payment) {
	
		Date d = new Date();
		Payment p = new Payment();
		p.setId(payment.getId());
		p.setProduit(payment.getProduit());
		p.setUser(payment.getUser());
		p.setQte_paye(payment.getQte_paye());
		p.setDate(d);
		
	
		pService.save(p);
		
		return "redirect:/dashboard";
		
		
	}
}
