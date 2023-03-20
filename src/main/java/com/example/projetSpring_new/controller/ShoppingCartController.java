package com.example.projetSpring_new.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.projetSpring_new.dto.CartItemData;
import com.example.projetSpring_new.dto.CartItemListParam;
import com.example.projetSpring_new.dto.PaymentDto;
import com.example.projetSpring_new.dto.PaymentDtoRepository;
import com.example.projetSpring_new.dto.UsersListParam;
import com.example.projetSpring_new.model.CartItem;
import com.example.projetSpring_new.model.GlobalData;
import com.example.projetSpring_new.model.Payment;
import com.example.projetSpring_new.model.User;
import com.example.projetSpring_new.repository.CartItemRepository;
import com.example.projetSpring_new.repository.PaymentRepository;
import com.example.projetSpring_new.model.Produit;
import com.example.projetSpring_new.service.CustomUserDetails;
import com.example.projetSpring_new.service.PaymentService;
import com.example.projetSpring_new.service.ProduitService;
import com.example.projetSpring_new.service.ShoppingCartService;
import com.example.projetSpring_new.service.UserService;

@Controller
public class ShoppingCartController {

	/*
	 * @Autowired private ShoppingCartService cartService;
	 * 
	 * @Autowired private UserService userService;
	 */
	/*
	 * @GetMapping("/cart") public String ShowShoppingCart(Model model,
	 * 
	 * @AuthenticationPrincipal Authentication authentication) { User user =
	 * userService.getCurrentlyLoggedInUser(authentication); List<CartItem>
	 * cartItems = cartService.listCartItems(user);
	 * 
	 * model.addAttribute("cartItems", cartItems); model.addAttribute("pageTitle",
	 * "Cart"); return "shopping_cart"; }
	 */

	@Autowired
	ProduitService produitService;
	@Autowired
	UserService userService;
	@Autowired
	ShoppingCartService cartService;
	@Autowired
	PaymentService paymentService;
	@Autowired
	private CartItemRepository comRepo;
	@Autowired
	private PaymentRepository payeRepo;
	@Autowired
	private PaymentDtoRepository payeDto;

	@GetMapping("/addToCart/{id}")
	public String addToCart(@PathVariable int id) {
		GlobalData.cart.add(produitService.get(id));

		// Integer userId = userDetails.getId();
		User user = userService.get(1);

		CartItem newCom = new CartItem();
		newCom.setProduit(produitService.get(id));
		newCom.setUser(user);
		newCom.setQte_com(1);

		comRepo.save(newCom);

		return "redirect:/shop";
	}

	@PostMapping("/cart/add")
	public String saveCartItem(@ModelAttribute("cartItem") CartItem cart,
			@RequestParam("produit_id") Integer produit_id, @RequestParam("user_id") Integer client_id, Model model,
			RedirectAttributes rm) {

		Produit produit = produitService.get(produit_id);
		User user = userService.get(client_id);
		CartItem newCom = new CartItem();
		Payment paye = new Payment();

		if (produit.getQte() < cart.getQte_com()) {

			// String referer = request.getHeader("Referer");

			rm.addFlashAttribute("msg", "La quantite dispponible est insuffisant!!!");
			return "redirect:/viewproduct1/" + produit.getId();

		} else {

//			int nouveau_qte = produit.getQte() - cart.getQte_com();
//			if (cart.getQte_com() > 0 && nouveau_qte >= 0) {
//				produit.setQte(nouveau_qte);
//				produitService.updateProduct(produit);
//			} else {
//				rm.addFlashAttribute("msg", "Entrer la quantite que vous voulez commander!!!");
//				return "redirect:/viewproduct1/" + produit.getId();
//			}
//			if (cart.getQte_com() > 0) {
//
//				Date date = new Date();
//
//				paye.setProduit(produit);
//				paye.setUser(user);
//				paye.setQte_paye(cart.getQte_com());
//				paye.setAdresse(null);
//				paye.setCp(null);
//				paye.setVille(null);
//				paye.setTel(null);
//				paye.setDate(date);
//				paye.setStatus(null);
//
//				payeRepo.save(paye);
//			}
			if (cart.getQte_com() > 0) {

				newCom.setId(cart.getId());
				newCom.setProduit(produit);
				newCom.setUser(user);
				newCom.setQte_com(cart.getQte_com());
				comRepo.save(newCom);

				rm.addFlashAttribute("success", "Produit ajoute au pannier");
				return "redirect:/shop";
			} else {
				rm.addFlashAttribute("msg", "Entrer la quantite que vous voulez commander!!!");
				return "redirect:/viewproduct1/" + produit.getId();
			}

		}

	}

	@RequestMapping("/cart/edit/{id}")

	public ModelAndView updateCartForm(@PathVariable(name = "id") Integer id,
			@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {

		ModelAndView mav = new ModelAndView("viewProduit_edit");
		// maka id ny produit ao am cart item
		CartItem cart = cartService.get(id);
		Produit produit = cart.getProduit();
		Integer pid = produit.getId();

		mav.addObject("cartItem", cart);
		Integer userEmail = userDetails.getUserId();
		User user = userService.get(userEmail);
		List<CartItem> l = cartService.listCartItems(user);
		model.addAttribute("cartCount", l.size());
		model.addAttribute("produit", produitService.get(pid));
		model.addAttribute("user", userDetails.getUserId());
		return mav;
	}

	@GetMapping("/cart")
	public String cartGet(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {

		if (userDetails == null) {

			model.addAttribute("cartCount", GlobalData.cart.size());
		} else {
			Integer userEmail = userDetails.getUserId();
			User user = userService.get(userEmail);
			List<CartItem> l = cartService.listCartItems(user);

			CartItemListParam userListParam = cartService.searchAll(user);
			model.addAttribute("userListParam", userListParam);

			// CartItemListParam cart = cartService.searchAll();

			model.addAttribute("cart", l);
			// model.addAttribute("cart", cart);
			model.addAttribute("cartCount", l.size());
			model.addAttribute("user", user);

		}
		model.addAttribute("userDetails", userDetails);
		return "shopping_cart";
	}

	@RequestMapping(value = "/cart/update", method = RequestMethod.POST)
	public String UpdateQteCart(@Validated @ModelAttribute CartItemListParam cartListParam, BindingResult result,
			Model model, RedirectAttributes rm) {
		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				if (!errorList.contains(error.getDefaultMessage())) {
					errorList.add(error.getDefaultMessage());
				}
			}
			model.addAttribute("validationError", errorList);
			return "shopping_cart";
		}

		cartService.updateAll(cartListParam);
		rm.addFlashAttribute("msg", "La quantite mettre à jour avec success!");
		return "redirect:/checkout";
	}

	@RequestMapping(value = "/cart/valider", method = RequestMethod.POST)
	public String ValiderCart(@Validated @ModelAttribute CartItemListParam cartListParam, BindingResult result,
			@AuthenticationPrincipal CustomUserDetails userDetails, Model model, RedirectAttributes rm,
			@RequestParam("ville") String ville, @RequestParam("cp") String cp, @RequestParam("adrs") String adrs,
			@RequestParam("tel") String tel, @RequestParam("user_id") Integer user_id) {

		if (result.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : result.getAllErrors()) {
				if (!errorList.contains(error.getDefaultMessage())) {
					errorList.add(error.getDefaultMessage());
				}
			}
			model.addAttribute("validationError", errorList);
			return "checkout";
		}

		List<Payment> payeList = new ArrayList<Payment>();
		Date date = new Date();
		for (CartItemData data : cartListParam.getCartItemDataList()) {
			// maka n qte anle produit am liste eo

			Produit produit = produitService.get(data.getProduit().getId());
			// qte anle produit atao moins qte com

			int nouveau_qte = produit.getQte() - data.getQte_com();
			// mise a jour anle produit rehefa vita ny commande
			produit.setQte(nouveau_qte);
			produitService.updateProduct(produit);

			// ajout ny donnee makany am table payment
			Payment paye = new Payment();

			paye.setProduit(data.getProduit());
			paye.setUser(data.getUser());
			paye.setQte_paye(data.getQte_com());
			paye.setDate(date);

			payeList.add(paye);
		}
		payeRepo.saveAll(payeList);

		Integer uid = userDetails.getUserId();
		User user = userService.get(uid);
		cartService.deleteByUser(user);

		// update adresse user
		userService.update(ville, adrs, cp, tel, user_id);

		rm.addFlashAttribute("msg",
				"Felicitation votre achat est effectuer avec succe; veuillez consulter votre facture ou acheter d'autre produit. Merci!!!");
		return "redirect:/invoice";
	}

	@RequestMapping("/cart/removeItem/{id}")
	public String cartItemRemove(@PathVariable(name = "id") Integer id, RedirectAttributes rm) {

		CartItem newCom = cartService.get(id);
		Produit produit = newCom.getProduit();

		int nouveau_qte = produit.getQte() + newCom.getQte_com();

		if (nouveau_qte != 0) {
			produit.setQte(nouveau_qte);
			produitService.updateProduct(produit);
			cartService.delete(id);
			// paymentService.delete(id);
			return "redirect:/cart";
		} else {
			rm.addFlashAttribute("error", "On peut pas enlever ce produit!!!");
			return "redirect:/cart";
		}

	}

	@RequestMapping("/cart/remove/{id}")
	public String cartItem(@PathVariable(name = "id") Integer id, RedirectAttributes rm) {

		CartItem newCom = cartService.get(id);
		Produit produit = newCom.getProduit();

		int nouveau_qte = produit.getQte() + newCom.getQte_com();
		if (nouveau_qte != 0) {
			produit.setQte(nouveau_qte);
			produitService.updateProduct(produit);
			cartService.delete(id);
			return "redirect:/";
		} else {
			rm.addFlashAttribute("error", "On peut pas enlever ce produit!!!");
			return "redirect:/cart";
		}

	}

	@GetMapping("/checkout")
	public String checkout(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
		if (userDetails == null) {

			model.addAttribute("cartCount", GlobalData.cart.size());
			return "redirect:/";
		} else {
			Integer userEmail = userDetails.getUserId();
			User user = userService.get(userEmail);
			List<CartItem> l = cartService.listCartItems(user);

			CartItemListParam userListParam = cartService.searchAll(user);
			model.addAttribute("userListParam", userListParam);

			// CartItemListParam cart = cartService.searchAll();

			model.addAttribute("cart", l);
			// model.addAttribute("cart", cart);
			model.addAttribute("cartCount", l.size());
			model.addAttribute("user", user);
		}

		model.addAttribute("userDetails", userDetails);
		return "checkout";

	}

//	@PostMapping("/checkout/add")
//	public String savePayment(@ModelAttribute("payments") Payment payment,
//
//			@AuthenticationPrincipal CustomUserDetails userDetails) {
//
//		/*
//		 * User u = userService.get(id); u.setAdrs(user.getAdrs());
//		 * userService.update(u);
//		 */
//		User u = new User();
//
//		Integer userId = userDetails.getUserId();
//		User user = userService.get(userId);
//	
//
//		return "redirect:/invoice";
//	}

	@GetMapping("/invoice")
	public String invoice(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
		if (userDetails == null) {

			model.addAttribute("cartCount", GlobalData.cart.size());
			return "redirect:/shop";
		} else {
			Integer userEmail = userDetails.getUserId();
			User user = userService.get(userEmail);
			List<Payment> l = paymentService.listPaymenByUsert(user);

			model.addAttribute("cart", l);
			//model.addAttribute("cartCount", l.size());
			model.addAttribute("user", user);
			// model.addAttribute("adresses", paymentService.adresseUser(user));
		}
		return "invoice";

	}

	@GetMapping("/invoice/print")
	public String invoicePrint(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
		if (userDetails == null) {

			model.addAttribute("cartCount", GlobalData.cart.size());
			return "redirect:/shop";
		} else {
			Integer userEmail = userDetails.getUserId();
			User user = userService.get(userEmail);
			Date date = new Date();
			List<Payment> l = paymentService.invoiceByUser(userEmail);

			model.addAttribute("cart", l);
			//model.addAttribute("cartCount", l.size());
			model.addAttribute("user", user);
		}
		return "invoice_print";

	}

}
