package com.ass.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Rates")
public class Rate implements Serializable{
	@Id
	int id;
	@ManyToOne @JoinColumn(name = "Username")
	Account account;
	@ManyToOne @JoinColumn(name = "ProductId")
	Product product;
	double point;
}
