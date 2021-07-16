package com.ass.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ass.model.Cart;

public interface CartDao extends JpaRepository<Cart, Integer>{

}
