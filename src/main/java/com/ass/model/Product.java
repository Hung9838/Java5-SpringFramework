package com.ass.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.core.sym.Name;

import lombok.Data;

@Data
@Entity
@Table(name = "Products")
public class Product implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String name;
	String image;
	String describe;
	@Temporal (TemporalType.DATE)
	Date createDate = new Date();
	@Column (name = "Availabe")
	Boolean avaliabe;
	Double promotions;
	@ManyToOne @JoinColumn(name = "CategoryName")
	Category category;
	Double price;
	@OneToMany(mappedBy = "product")
	List<OrderDetail> orderDetail;
	@OneToMany(mappedBy = "product")
	List<CartsDetail> cartsDetail;
}
