package com.ass.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ass.model.Category;

public interface CategoryDao extends JpaRepository<Category, String>{
	@Query("SELECT c FROM Category c WHERE c.active= true")
	List<Category> LoadCategories();
	
}
