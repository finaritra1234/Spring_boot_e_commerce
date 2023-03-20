package com.example.projetSpring_new.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.projetSpring_new.model.Produit;
import com.example.projetSpring_new.model.CartItem;
import com.example.projetSpring_new.model.GlobalData;
import com.example.projetSpring_new.model.User;
import com.example.projetSpring_new.service.CustomUserDetails;
import com.example.projetSpring_new.service.ProduitService;
import com.example.projetSpring_new.service.ShoppingCartService;
import com.example.projetSpring_new.service.UserService;

@Controller
public class HomeController {

	@Autowired
	ProduitService produitService;
	@Autowired
	UserService userService;
	@Autowired
	ShoppingCartService cartService;

	@RequestMapping({ "/", "/home" })
	public String home(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
		if (userDetails == null) {

			model.addAttribute("cartCount", 0);
		} else {
			Integer userEmail = userDetails.getUserId();
			User user = userService.get(userEmail);
			List<CartItem> l = cartService.listCartItems(user);
			model.addAttribute("cart", l);
			model.addAttribute("cartCount", l.size());
		}
		List<Produit> prod = produitService.listNouveau();
		List<Produit> laptop = produitService.listLaptop();
		List<Produit> mobile = produitService.listMobile();
		model.addAttribute("produits", produitService.listAll());
		model.addAttribute("listenouveaute", prod);
		model.addAttribute("laptop", laptop);
		model.addAttribute("mobile", mobile);
		model.addAttribute("userDetails", userDetails);
		return viewPage(model, 1, "id", "asc");
	}

	@RequestMapping("/page/{pageNum}")
	public String viewPage(Model model, @PathVariable(name = "pageNum") int pageNum,
			@Param("sortField") String sortField, @Param("sortDir") String sortDir) {

		Page<Produit> page = produitService.listAll(pageNum, sortField, sortDir);

		List<Produit> listProducts = page.getContent();

		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listProducts", listProducts);

		return "index";
	}

	@RequestMapping("/shop/page/{pageNum}")
	public String viewShopPage(Model model, @PathVariable(name = "pageNum") int pageNum,
			@Param("sortField") String sortField, @Param("sortDir") String sortDir) {

		Page<Produit> page = produitService.listShopAll(pageNum, sortField, sortDir);

		List<Produit> listProducts = page.getContent();

		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("produits", listProducts);

		return "shop";
	}

	@GetMapping("/shop")
	public String shop(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
		if (userDetails == null) {

			model.addAttribute("cartCount", 0);
		} else {
			Integer userEmail = userDetails.getUserId();
			User user = userService.get(userEmail);
			List<CartItem> l = cartService.listCartItems(user);

			model.addAttribute("cart", l);
			model.addAttribute("cartCount", l.size());
		}
		// model.addAttribute("produits", produitService.listAll());
		model.addAttribute("userDetails", userDetails);
		return viewShopPage(model, 1, "id", "asc");
	}

	@GetMapping("/shop/viewproduct/{id}")
	public String shopViewProduitById(Model model, @PathVariable Integer id,
			@AuthenticationPrincipal CustomUserDetails userDetails) {
		if (userDetails == null) {

			model.addAttribute("cartCount", 0);
		} else {
			Integer userEmail = userDetails.getUserId();
			User user = userService.get(userEmail);
			List<CartItem> l = cartService.listCartItems(user);
		
			model.addAttribute("cart", l);
			model.addAttribute("cartCount", l.size());
		}
		model.addAttribute("msg", "Stock de produit est insuffisant");
		model.addAttribute("produit", produitService.get(id));
		return "viewProduit";
	}

	@GetMapping("/viewproduct1/{id}")
	public String shopView1ProduitById(Model model, @PathVariable Integer id,
			@AuthenticationPrincipal CustomUserDetails userDetails) {
		if (userDetails == null) {

			model.addAttribute("cartCount", 0);
			model.addAttribute("produit", produitService.get(id));
		} else {
			Integer userEmail = userDetails.getUserId();
			User user = userService.get(userEmail);
			List<CartItem> l = cartService.listCartItems(user);
			model.addAttribute("cartCount", l.size());
			model.addAttribute("cart", l);
			model.addAttribute("produit", produitService.get(id));
			model.addAttribute("user", userDetails.getUserId());
		}
		model.addAttribute("userDetails", userDetails);
		return "viewProduit1";
	}
	
	//requete sql native

	@GetMapping("/laptop")
	public String shopLaptop(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
		if (userDetails == null) {

			model.addAttribute("cartCount", 0);
		} else {
			Integer userEmail = userDetails.getUserId();
			User user = userService.get(userEmail);
			List<CartItem> l = cartService.listCartItems(user);
			model.addAttribute("cartCount", l.size());
		}
		
		model.addAttribute("produits", produitService.listLaptop());
		return "shop";
	}

	@GetMapping("/mobile")
	public String shopMobile(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
		if (userDetails == null) {

			model.addAttribute("cartCount", 0);
		} else {
			Integer userEmail = userDetails.getUserId();
			User user = userService.get(userEmail);
			List<CartItem> l = cartService.listCartItems(user);
			model.addAttribute("cartCount", l.size());
		}
		model.addAttribute("produits", produitService.listMobile());
		return "shop";
	}

	@GetMapping("/accessoire")
	public String shopAccessoire(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
		if (userDetails == null) {

			model.addAttribute("cartCount", 0);
		} else {
			Integer userEmail = userDetails.getUserId();
			User user = userService.get(userEmail);
			List<CartItem> l = cartService.listCartItems(user);
			model.addAttribute("cartCount", l.size());
		}
		model.addAttribute("produits", produitService.listAccessoire());
		return "shop";
	}

	@GetMapping("/hp")
	public String shopHp(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
		if (userDetails == null) {

			model.addAttribute("cartCount", 0);
		} else {
			Integer userEmail = userDetails.getUserId();
			User user = userService.get(userEmail);
			List<CartItem> l = cartService.listCartItems(user);
			model.addAttribute("cartCount", l.size());
		}
		model.addAttribute("produits", produitService.listHp());
		return "shop";
	}

	@GetMapping("/acer")
	public String shopAcer(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
		if (userDetails == null) {

			model.addAttribute("cartCount", 0);
		} else {
			Integer userEmail = userDetails.getUserId();
			User user = userService.get(userEmail);
			List<CartItem> l = cartService.listCartItems(user);
			model.addAttribute("cartCount", l.size());
		}
		model.addAttribute("produits", produitService.listAcer());
		return "shop";
	}

	@GetMapping("/asus")
	public String shopAsus(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
		if (userDetails == null) {

			model.addAttribute("cartCount", 0);
		} else {
			Integer userEmail = userDetails.getUserId();
			User user = userService.get(userEmail);
			List<CartItem> l = cartService.listCartItems(user);
			model.addAttribute("cartCount", l.size());
		}
		model.addAttribute("produits", produitService.listAsus());
		return "shop";
	}

	@GetMapping("/samsung")
	public String shopSamsung(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
		if (userDetails == null) {

			model.addAttribute("cartCount", 0);
		} else {
			Integer userEmail = userDetails.getUserId();
			User user = userService.get(userEmail);
			List<CartItem> l = cartService.listCartItems(user);
			model.addAttribute("cartCount", l.size());
		}
		model.addAttribute("produits", produitService.listSamsung());
		return "shop";
	}

	@GetMapping("/itel")
	public String shopItel(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
		if (userDetails == null) {

			model.addAttribute("cartCount", 0);
		} else {
			Integer userEmail = userDetails.getUserId();
			User user = userService.get(userEmail);
			List<CartItem> l = cartService.listCartItems(user);
			model.addAttribute("cartCount", l.size());
		}
		model.addAttribute("produits", produitService.listItel());
		return "shop";
	}

	@GetMapping("/redmi")
	public String shopRedmi(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
		if (userDetails == null) {

			model.addAttribute("cartCount", 0);
		} else {
			Integer userEmail = userDetails.getUserId();
			User user = userService.get(userEmail);
			List<CartItem> l = cartService.listCartItems(user);
			model.addAttribute("cartCount", l.size());
		}
		model.addAttribute("produits", produitService.listRedmi());
		return "shop";
	}

	@GetMapping("/casque")
	public String shopCasque(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
		if (userDetails == null) {

			model.addAttribute("cartCount", 0);
		} else {
			Integer userEmail = userDetails.getUserId();
			User user = userService.get(userEmail);
			List<CartItem> l = cartService.listCartItems(user);
			model.addAttribute("cartCount", l.size());
		}
		model.addAttribute("produits", produitService.listCasque());
		return "shop";
	}

	@GetMapping("/appareil")
	public String shopAppareil(@AuthenticationPrincipal CustomUserDetails userDetails, Model model) {
		if (userDetails == null) {

			model.addAttribute("cartCount", 0);
		} else {
			Integer userEmail = userDetails.getUserId();
			User user = userService.get(userEmail);
			List<CartItem> l = cartService.listCartItems(user);
			model.addAttribute("cartCount", l.size());
		}
		model.addAttribute("produits", produitService.listAppareil());
		return "shop";
	}

	@GetMapping("/cherche")
	public String shopCherche(@RequestParam("mot") String mot, @AuthenticationPrincipal CustomUserDetails userDetails,
			Model model) {
		if (userDetails == null) {

			model.addAttribute("cartCount", 0);
		} else {
			Integer userEmail = userDetails.getUserId();
			User user = userService.get(userEmail);
			List<CartItem> l = cartService.listCartItems(user);
			model.addAttribute("cartCount", l.size());
		}
		model.addAttribute("produits", produitService.findByNomLike(mot));
		return "shop";
	}

}
