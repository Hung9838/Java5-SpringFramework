package com.ass.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Carts")
public class Cart implements Serializable{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@ManyToOne @JoinColumn(name = "Username")
	Account account;
	@OneToMany (mappedBy = "cart")
	List<CartsDetail> cartsDetails;
}
