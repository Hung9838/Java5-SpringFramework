package com.ass.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Accounts")
public class Account implements Serializable{
	@Id
	String username;
	String password;
	boolean role;
	String fullname;
	String phoneNumber;
	String email;
	String photo;
	@OneToMany(mappedBy = "account")
	List<Order> orders;
	@OneToMany(mappedBy = "account")
	List<Cart> carts;
	@OneToMany(mappedBy = "account")
	List<Rate> rates;
}
