package com.ass.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ass.model.CartsDetail;

public interface CartDetailDao extends JpaRepository<CartsDetail, Integer>{
	
}
