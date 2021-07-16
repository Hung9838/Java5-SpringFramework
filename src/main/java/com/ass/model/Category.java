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
@Table(name = "Categories")
public class Category implements Serializable{
	@Id
	String name;
	boolean active;
	@OneToMany(mappedBy = "category")
	List<Product> products;
}
