package com.ass.modelQuery;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.ass.model.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TopSelling {
	@Id
	int Id;
	String Name;
	String Image;
	Double Price;
	String Describe;
	Double Promotions;
	Category CategoryName;
	Integer sl;
}
