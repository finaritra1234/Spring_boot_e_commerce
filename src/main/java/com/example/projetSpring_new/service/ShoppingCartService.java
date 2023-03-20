package com.example.projetSpring_new.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projetSpring_new.dto.CartItemData;
import com.example.projetSpring_new.dto.CartItemListParam;
import com.example.projetSpring_new.dto.UsersData;
import com.example.projetSpring_new.dto.UsersListParam;
import com.example.projetSpring_new.model.CartItem;
import com.example.projetSpring_new.model.Payment;
import com.example.projetSpring_new.model.Produit;
import com.example.projetSpring_new.model.User;
import com.example.projetSpring_new.model.Users;

import com.example.projetSpring_new.repository.CartItemRepository;
import com.example.projetSpring_new.repository.PaymentRepository;

@Service
public class ShoppingCartService {
	@Autowired
	private CartItemRepository cartRepo;
	@Autowired
	private PaymentRepository payeRepo;
	

	public List<CartItem> listCartItems(User user) {
		return cartRepo.findByUser(user);
	}
	
	public CartItemListParam searchAll(User user) {
		   
	    List<CartItem> userList = cartRepo.findByUser(user);
	    CartItemListParam userListParam = new CartItemListParam();
	    List<CartItemData> list = new ArrayList<CartItemData>();
	  
	    for(CartItem cart : userList) {
	    	CartItemData data = new CartItemData();
	      data.setId(cart.getId());
	      data.setProduit(cart.getProduit());
	      data.setUser(cart.getUser());
	      data.setQte_com(cart.getQte_com());
	      
	      list.add(data);
	    }
	    userListParam.setCartItemDataList(list);
	    return userListParam;
	  }
	
	public void updateAll(CartItemListParam param) {
	    List<CartItem> cartList = new ArrayList<CartItem>();
	    
	    for (CartItemData data : param.getCartItemDataList()) {
	    	CartItem cart = cartRepo.findById(data.getId()).get();
	    	cart.setQte_com(data.getQte_com());
	    	
	    	cartList.add(cart);
	    }
	    cartRepo.saveAll(cartList);
	  }
	
	public void addAll(CartItemListParam param) {
		  List<Payment> payeList = new ArrayList<Payment>();
	    Date date = new Date();
	    for (CartItemData data : param.getCartItemDataList()) {
	    	Payment paye = new Payment();
	    	paye.setProduit(data.getProduit());
	    	paye.setUser(data.getUser());
	    	paye.setQte_paye(data.getQte_com());
	    
	    	paye.setDate(date);
	    
	    	
	    	payeList.add(paye);
	    }
	    payeRepo.saveAll(payeList);
	  }

	
	public CartItem save(CartItem c) {
		return cartRepo.save(c);
	}

	public List<CartItem> listCartByproduit(Produit p) {
		return cartRepo.findByProduit(p);
	}

	public void delete(Integer id) {
		cartRepo.deleteById(id);

	}

	public CartItem get(Integer id) {
		return cartRepo.findById(id).get();
	}

	public CartItem updateProduct(CartItem p) {
		CartItem p1;
		p1 = cartRepo.findById(p.getId()).get();

		p1.setQte_com(p1.getQte_com());

		return cartRepo.save(p1);

	}

	@Transactional
	public void deleteByUser(User user) {
		cartRepo.deleteByUser(user);
	}

}
