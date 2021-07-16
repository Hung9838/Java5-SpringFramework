package com.ass.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ass.model.Order;

public interface OrderDao extends JpaRepository<Order, Integer>{
	@Query("SELECT o FROM Order o where o.account.username = :usname")
	List<Order> findOrderUser(@Param("usname") String usname);
	
	@Query("SELECT o FROM Order o where o.account.username = :usname and o.status = :status")
	List<Order> findOrderUserStatus(@Param("usname") String usname, @Param("status") String status);
}
