package com.ass.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemModel {
	Integer id;
	String img;
	String name;
	int qty;
	Double price;
}
