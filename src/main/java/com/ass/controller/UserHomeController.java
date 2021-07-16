package com.ass.controller;

import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ass.dao.CategoryDao;
import com.ass.dao.ProductDao;
import com.ass.model.Account;
import com.ass.model.Category;
import com.ass.model.Product;
import com.ass.modelQuery.TopSelling;
import com.sun.mail.iap.Response;

@Controller
public class UserHomeController {
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	ProductDao productDao;
	
	@GetMapping("/user")
	public String home(Model model) {
		//Load select 
		model.addAttribute("ListCategories", categoryDao.LoadCategories());
		
		//Load top sale
		List<Product> topSale = productDao.TopSale();
		model.addAttribute("TopSaleProduct", topSale);
		
		
		return "user/home";
	}
		
}
