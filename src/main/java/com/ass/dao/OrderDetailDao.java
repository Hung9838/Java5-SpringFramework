package com.ass.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ass.model.OrderDetail;
import com.ass.modelQuery.CategoryReport;
import com.ass.modelQuery.ProductReport;
import com.ass.modelQuery.TimeReport;

public interface OrderDetailDao extends JpaRepository<OrderDetail, Integer>{
	
	@Query("SELECT od FROM OrderDetail od where od.order.id=:orid")
	List<OrderDetail> findOrderORID(@Param("orid") Integer orderId); 
	
	@Query("SELECT new CategoryReport(d.product.category, count(d.id), sum(d.quantity * d.price)) FROM OrderDetail d GROUP BY d.product.category")
	Page<CategoryReport> FindCategoryReport(Pageable pageable);
	
	@Query("SELECT new ProductReport(d.product, count(d.id), sum(d.quantity * d.price)) FROM OrderDetail d GROUP BY d.product")
	Page<ProductReport> FindProductReport(Pageable pageable);
	
	@Query("SELECT new com.ass.modelQuery.TimeReport(count(d.id), sum(d.quantity * d.price)) FROM OrderDetail d WHERE d.order.createDate BETWEEN ?1 AND ?2")
	TimeReport findTimeReport(Date dateForm,Date dateEnd);
}
