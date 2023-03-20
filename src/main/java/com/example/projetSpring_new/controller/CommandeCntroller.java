package com.example.projetSpring_new.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.projetSpring_new.model.Payment;
import com.example.projetSpring_new.service.ClientService;
import com.example.projetSpring_new.service.CommandeService;
import com.example.projetSpring_new.service.UserService;

@Controller
public class CommandeCntroller {
	@Autowired
	private CommandeService pService;
	@Autowired
	private UserService cliService;
	
	@RequestMapping("/commande")
	public String viewDashoard(Model model) {
		
		// List<Payment> page = pService.allPayment();
		
		// model.addAttribute("listPayes", page);
		 return viewPageCommadne(model, 1, "id", "asc");
	}
	@RequestMapping("/commande/page/{numPage}")
	public String viewPageCommadne(Model model, @PathVariable(name = "numPage") int pageNum,
			@Param("sortField") String sortField, @Param("sortDir") String sortDir) {

		Page<Payment> page = pService.listAll(pageNum, sortField, sortDir);

		List<Payment> listPaye = page.getContent();
		
		Date date= new Date();

		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("listPayes", listPaye);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		
		 
		 model.addAttribute("sumPayement",pService.sumPayement());
		 model.addAttribute("sumPayementNow",pService.sumPayementNow(date));
		 model.addAttribute("countPayement",pService.countPayement());
		 model.addAttribute("countPayementNow",pService.countPayementNow(date));

		return "commande";
	}
	@RequestMapping("/commandeNow")
	public String payeNow(Model model) {	
		Date date= new Date();
		
		List<Payment> page = pService.payeNow(date);
		 model.addAttribute("sumPayement",pService.sumPayement());
		 model.addAttribute("sumPayementNow",pService.sumPayementNow(date));
		 model.addAttribute("countPayement",pService.countPayement());
		 model.addAttribute("countPayementNow",pService.countPayementNow(date));
		 model.addAttribute("listPayes", page);
		return "commande";
	}
}
