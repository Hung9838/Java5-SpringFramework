package com.ass.model;

import java.util.Date;
import java.util.List;

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

import lombok.Data;

@Data
@Entity
@Table(name = "Orders")
public class Order {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@ManyToOne @JoinColumn(name = "Username")
	Account account;
	@Temporal(TemporalType.DATE)
	Date createDate = new Date();
	String address;
	String status;
	@OneToMany(mappedBy = "order")
	List<OrderDetail> orderDetails;
}
