package com.ass.modelQuery;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.ass.model.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor 
@Entity
public class ProductReport {
	@Id
	private Product sanPham;
	private long soDonHang;
	private double doanhThu;
}
