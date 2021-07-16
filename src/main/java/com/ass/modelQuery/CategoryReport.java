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
public class CategoryReport {
	@Id
	private Category danhMuc;
	private long soDonHang;
	private double doanhThu;
}
