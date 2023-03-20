package com.example.projetSpring_new.dto;

import java.io.Serializable;
import java.util.List;

public class CartItemListParam implements Serializable {
	private List<CartItemData> cartItemDataList;

	public List<CartItemData> getCartItemDataList() {
		return cartItemDataList;
	}

	public void setCartItemDataList(List<CartItemData> cartItemDataList) {
		this.cartItemDataList = cartItemDataList;
	}

	

}
