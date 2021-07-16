package com.ass.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CartsDetails")
public class CartsDetail implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@ManyToOne @JoinColumn(name = "CartId")
	Cart cart;
	@ManyToOne @JoinColumn(name = "ProductId")
	Product product;
	int quantity;
	Double price;
}
