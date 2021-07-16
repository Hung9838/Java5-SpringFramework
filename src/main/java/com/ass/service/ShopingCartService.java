package com.ass.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.ass.model.Product;

@SessionScope
@Service
public class ShopingCartService {
	List<CartItemModel> listCart = new ArrayList<CartItemModel>();
	
	public void add(Product product) {
		boolean temp = false;
		if (listCart != null) {
			for (CartItemModel item : listCart) {
				if (item.getId() == product.getId()) {
					item.setQty(item.getQty()+1);
					double promotions = product.getPromotions() == null ? 0:product.getPromotions(); 
					double price = product.getPrice() / 100 * (100 - promotions);
					item.setPrice(item.getQty()*price);
					temp = true;
				}
			}
		}
		if (temp == false) {
			CartItemModel cart = new CartItemModel();
			cart.setId(product.getId());
			cart.setImg(product.getImage());
			cart.setName(product.getName());
			cart.setQty(1);
			double promotions = product.getPromotions() == null ? 0:product.getPromotions(); 
			double price = product.getPrice() / 100 * (100 - promotions);
			cart.setPrice(price);
			listCart.add(cart);
		}
	}
	
	public void redution(Product product) {
		CartItemModel cart = null;
		for (CartItemModel item : listCart) {
			if (item.getId() == product.getId()) {
				cart = item;
			}
		}
		cart.setQty(cart.getQty()-1);
		double promotions = product.getPromotions() == null ? 0:product.getPromotions(); 
		double price = product.getPrice() / 100 * (100 - promotions);
		cart.setPrice(cart.getQty()*price);
		if (cart.getQty() == 0) {
			listCart.remove(cart);
		}
	}
	
	public void remove(Product product) {
		CartItemModel cart = null;
		for (CartItemModel item : listCart) {
			if (item.getId() == product.getId()) {
				cart = item;
			}
		}
		listCart.remove(cart);
	}
	
	public List<CartItemModel> getList() {
		
		return listCart;
	}
	
	public void clear() {
		listCart.removeAll(listCart);
	}
}
