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
@Table(name = "OrderDetails")
public class OrderDetail implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@ManyToOne @JoinColumn(name = "OrderId")
	Order order;
	@ManyToOne @JoinColumn(name = "ProductId")
	Product product;
	double price;
	int quantity;
}
