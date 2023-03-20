package com.example.projetSpring_new.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;


import com.example.projetSpring_new.model.Client;

import com.example.projetSpring_new.service.ClientService;

@Controller
public class ClientController {
	@Autowired
	private ClientService service;

	@RequestMapping("/client")

	public String viewListe(Model model) {

		List<Client> listCli = service.listAll();
		model.addAttribute("listClients", listCli);

		return "liste_client";
	}

	@RequestMapping("/newClient")

	public String showNewClientForm(Model model) {

		Client cli = new Client();
		model.addAttribute("clients", cli);
		// model.addAttribute("mety",true);
		return "ajout_client";
	}

	@RequestMapping(value = "/savecli", method = RequestMethod.POST)
	public String saveClient(@ModelAttribute("clients") Client cli, Model model) {
		service.save(cli);

		List<Client> listCli = service.listAll();
		model.addAttribute("listClients", listCli);
		model.addAttribute("mety", true);
		return "liste_client";
	}

	@RequestMapping("/editcli/{id}")

	public ModelAndView showEditClientForm(@PathVariable(name = "id") Integer id) {

		ModelAndView mav = new ModelAndView("edit_client");

		Client client = service.get(id);
		mav.addObject("clients", client);

		return mav;
	}

	@RequestMapping("/deletecli/{id}")
	public String deleteProduit(@PathVariable(name = "id") Integer id, Model model) {
		service.delete(id);

		List<Client> listCli = service.listAll();
		model.addAttribute("listClients", listCli);
		model.addAttribute("mety", true);
		return "liste_client";
	}

	@RequestMapping("/commandecli/{id}")

	public ModelAndView showCommendeCli(@PathVariable(name = "id") Integer id) {

		ModelAndView mav = new ModelAndView("commande_client");

		Client client = service.get(id);
		mav.addObject("clients", client);

		return mav;
	}

}