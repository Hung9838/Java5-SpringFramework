package com.ass.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ass.model.Category;
import com.ass.model.Product;
import com.ass.modelQuery.TopSelling;

public interface ProductDao extends JpaRepository<Product, Integer>{
	
//	@Query("SELECT p FROM Product p ORDER BY p.promotions DESC")
	@Query(value = "select top 6 * from Products where Availabe = 1 order by Promotions desc", 
			nativeQuery = true)
	List<Product> TopSale();
	
//	@Query(value = "select top 6 Products.Id,Name,Image,Products.Price,Describe,Promotions,CategoryName,SUM(Quantity)\r\n"
//			+ "	from Products left outer join OrderDetails on Products.Id = OrderDetails.ProductId \r\n"
//			+ "	where Availabe = 1\r\n"
//			+ "	GROUP BY Products.Id,Name,Image,Products.Price,Describe,CategoryName,Promotions \r\n"
//			+ "	ORDER BY SUM(Quantity) DESC", 
//			nativeQuery = true)
//	@Query(value = "SELECT ",nativeQuery = true)
//	List<TopSelling> TopSelling();
	
	@Query("SELECT p FROM Product p WHERE p.category=:ct and p.avaliabe=true")
	List<Product> finProductCT(@Param("ct") Optional<Category> optional);
}
