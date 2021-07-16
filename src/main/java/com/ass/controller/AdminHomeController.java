package com.ass.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ass.dao.CategoryDao;
import com.ass.dao.OrderDao;
import com.ass.dao.OrderDetailDao;
import com.ass.dao.ProductDao;
import com.ass.model.Category;
import com.ass.model.Order;
import com.ass.model.OrderDetail;
import com.ass.model.Product;


@Controller
public class AdminHomeController {
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	OrderDetailDao orderDetailDao;
		
	@GetMapping("/admin/category")
	public String category(Model model) {		
		Pageable pageable = PageRequest.of(0, 3); 		
		Page<Category> page = categoryDao.findAll(pageable);
		model.addAttribute("page", page);
		
		model.addAttribute("view", "/views/admin/category.jsp");
		return "admin/home";
	}
	
	@GetMapping("/admin/category/page")
	public String paginateCT(Model model,
			@RequestParam("p") Optional<Integer> p) {
			if (p.get() == -1) {
				return "redirect:/admin/category";
			}
			Pageable pageable = PageRequest.of(p.orElse(0), 3);
			Page<Category> page = categoryDao.findAll(pageable);
			model.addAttribute("page", page);			
			model.addAttribute("view", "/views/admin/category.jsp");
		return "admin/home";
	}
		
	@GetMapping("/admin/product")
	public String product(Model model) {
		List<Category> listSelectCT = categoryDao.findAll();
		model.addAttribute("listSelectCT", listSelectCT);
		
		Pageable pageable = PageRequest.of(0, 3); 		
		Page<Product> page = productDao.findAll(pageable);
		model.addAttribute("pagePT", page);
		
		model.addAttribute("view", "/views/admin/product.jsp");
		return "admin/home";
	}
	
	@GetMapping("/admin/product/page")
	public String paginatePT(Model model,
			@RequestParam("p") Optional<Integer> p) {
			if (p.get() == -1) {
				return "redirect:/admin/product";
			}
			
			List<Category> listSelectCT = categoryDao.findAll();
			model.addAttribute("listSelectCT", listSelectCT);
			
			Pageable pageable = PageRequest.of(p.orElse(0), 3);
			Page<Product> page = productDao.findAll(pageable);
			model.addAttribute("pagePT", page);			
			model.addAttribute("view", "/views/admin/product.jsp");
		return "admin/home";
	}
	
	@GetMapping("/admin/order")
	public String order(Model model) {		
		Pageable pageable = PageRequest.of(0, 3); 		
		Page<Order> page = orderDao.findAll(pageable);
		model.addAttribute("pageOR", page);
		String[] orListStatus = {"Chờ xác nhận", "Chờ lấy hàng", "Đang giao", "Đã giao", "Đã hủy"};
		model.addAttribute("orListStatus", orListStatus);
		model.addAttribute("view", "/views/admin/order.jsp");
		return "admin/home";
	}
	
	@GetMapping("/admin/order/page")
	public String paginateOR(Model model,
			@RequestParam("p") Optional<Integer> p) {
			if (p.get() == -1) {
				return "redirect:/admin/order";
			}			
			
			Pageable pageable = PageRequest.of(p.orElse(0), 3);
			Page<Order> page = orderDao.findAll(pageable);
			model.addAttribute("pageOR", page);
			String[] orListStatus = {"Chờ xác nhận", "Chờ lấy hàng", "Đang giao", "Đã giao", "Đã hủy"};
			model.addAttribute("orListStatus", orListStatus);
			model.addAttribute("view", "/views/admin/order.jsp");
		return "admin/home";
	}
		
	
//	@GetMapping("/admin/orderDetail/page")
//	public String paginateORDT(Model model,
//			@RequestParam("p") Optional<Integer> p) {
//			if (p.get() == -1) {
//				return "redirect:/admin/orderDetail?orderId="+orderId;
//			}			
//			
//			Pageable pageable = PageRequest.of(p.orElse(0), 3);
//			Page<OrderDetail> page = orderDetailDao.findOrderORID(orderId, pageable);
//			model.addAttribute("pageORDT", page);			
//			model.addAttribute("view", "/views/admin/order.jsp");
//		return "admin/home";
//	}
}
