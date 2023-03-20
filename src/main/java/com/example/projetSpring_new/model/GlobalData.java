package com.example.projetSpring_new.model;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {

	public static List<Produit> cart;
	static {
		cart = new ArrayList<Produit>();
	}
}
