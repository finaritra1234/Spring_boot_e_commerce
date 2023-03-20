package com.example.projetSpring_new.controller;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.projetSpring_new.model.CartItem;
import com.example.projetSpring_new.model.Produit;
import com.example.projetSpring_new.model.User;
import com.example.projetSpring_new.service.CustomUserDetails;
import com.example.projetSpring_new.service.ProduitService;

@Controller
public class ProduitController {
	
	String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/produitImages";
	
	@Autowired
	private ProduitService service;
	

	
	
	@RequestMapping("/produit")
	
	public String viewHomePage(Model model) {
		
		List<Produit> listProduits = service.listAll();
		model.addAttribute("listProduits",listProduits);
		
		return "liste_produit";
	}
	
	@RequestMapping("/newProduit")
	
	public String showNewProduitForm(Model model) {
		
		Produit produit = new Produit();
		model.addAttribute("produits",produit);
		//model.addAttribute("mety",true);
		return "ajout_produit";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduit(@ModelAttribute("produit") Produit produit, Model model) {
		service.save(produit);
		
		List<Produit> listProduits = service.listAll();
		model.addAttribute("listProduits",listProduits);
		model.addAttribute("mety",true);
		return "liste_produit";
	}
	@PostMapping("/produits/add")
	public String saveProduits(@ModelAttribute("produit") Produit produit,
			@RequestParam("produitImage")MultipartFile file,
			@RequestParam("image")String image) throws IOException {
	
		Produit p = new Produit();
		p.setId(produit.getId());
		p.setNom(produit.getNom());
		p.setRef_produit(produit.getRef_produit());
		p.setDate_creation(produit.getDate_creation());
		p.setPrixu(produit.getPrixu());
		p.setQte(produit.getQte());
		String imageUUID;
		if(!file.isEmpty()) {
			imageUUID = file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
			Files.write(fileNameAndPath, file.getBytes());
			
		} else {
			imageUUID = image;
		}
		p.setCategorie(produit.getCategorie());
		
		p.setImage(imageUUID);
		service.save(p);
		
		return "redirect:/produit";
		
		
	}
	
	
	
	@RequestMapping("/edit/{id}")
	
	public ModelAndView showEditProduitForm(@PathVariable(name = "id") Integer id) {
		
		ModelAndView mav = new ModelAndView("edit_produit");
		
		Produit produit = service.get(id);
		mav.addObject("produits", produit);
		
		return mav;
	}
	@RequestMapping("/delete/{id}")
	public String deleteProduit(@PathVariable(name = "id") Integer id, Model model) {
		service.delete(id);
		
		List<Produit> listProduits = service.listAll();
		model.addAttribute("listProduits",listProduits);
		model.addAttribute("mety",true);
		return "liste_produit";
	}
	
	//requete
	@GetMapping("/find")
	public String produitCherche(@RequestParam("mot") String mot,
			Model model) {
	
		model.addAttribute("listProduits", service.find(mot));
		return "liste_produit";
	}
	
	
}
