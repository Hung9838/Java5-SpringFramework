package com.ass.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ass.dao.OrderDao;
import com.ass.dao.OrderDetailDao;
import com.ass.dao.ProductDao;
import com.ass.model.OrderDetail;
import com.ass.model.Product;

@Controller
public class OrderDetailManagerController {
	List<OrderDetail> temp = new ArrayList<>();
	
	@Autowired
	OrderDetailDao orderDetailDao;
	
	@Autowired 
	OrderDao orderDao; 
	
	@Autowired 
	ProductDao productDao;
	
	@GetMapping("/admin/orderDetail")
	public String orderDetail(Model model,
			@RequestParam("orderId") Integer id) {
		List<OrderDetail> list = orderDetailDao.findOrderORID(id);
		temp = list;
		for (OrderDetail item : list) {
			System.out.println(item.getId());
		}
		model.addAttribute("ListORDT", list);
		model.addAttribute("OrderID", id);
		model.addAttribute("view", "/views/admin/orderDetail.jsp");
		return "admin/home";
	}
	
	@PostMapping("/admin/orderDetail/crud/add")
	public String add(
			@RequestParam(name = "id", defaultValue = "-1") Integer id,
			@RequestParam("orderID") Integer orderid,
			@RequestParam("product") Integer product,
			@RequestParam(name = "quantity", defaultValue = "1") Integer quantity,
			@RequestParam(name = "price", defaultValue = "0") Double price) {
		OrderDetail ordt = new OrderDetail();
		
		ordt.setOrder(orderDao.findById(orderid).get());
		Product pt = new Product();
		try {
			productDao.findById(product).get();
		} catch (Exception e) {
			return "redirect:/admin/orderDetail?orderId="+orderid;
		}
		ordt.setProduct(pt);
		ordt.setQuantity(quantity);
		ordt.setPrice(price);
		if (id != -1) {
			ordt.setId(id);
		}else {
			for (OrderDetail or : temp) {
				if (or.getProduct().getId() == pt.getId()) {
					System.out.println("co vao");
					ordt.setId(or.getId());
					ordt.setQuantity(quantity + or.getQuantity());
					orderDetailDao.save(ordt);
					return "redirect:/admin/orderDetail?orderId="+orderid;
				}
			}
		}	
		orderDetailDao.save(ordt);		
		return "redirect:/admin/orderDetail?orderId="+orderid;
	}
	
	@GetMapping("/admin/orderDetail/crud/edit")
	public String edit(Model model,
			@RequestParam("id") Integer id) {
		OrderDetail or = orderDetailDao.findById(id).get();
		model.addAttribute("ORDTForm", or);
		return "forward:/admin/orderDetail?orderId="+or.getOrder().getId();
	}
	
	@GetMapping("/admin/orderDetail/crud/delete")
	public String delete(Model model,
			@RequestParam("id") Integer id) {
		OrderDetail or = orderDetailDao.findById(id).get();
		orderDetailDao.deleteById(id);
		return "forward:/admin/orderDetail?orderId="+or.getOrder().getId();
	}
	
	@GetMapping("/admin/orderDetail/crud//loadProductDetail")
	public String loadProductDetail(Model model,
			@RequestParam("id") Integer id,
			@RequestParam("idPR") Integer idPR) {
		OrderDetail or = orderDetailDao.findById(id).get();
		Product pd = productDao.findById(idPR).get();
		model.addAttribute("infoPD", pd);
		System.out.println("alo");
		return "forward:/admin/orderDetail?orderId="+or.getOrder().getId();
	}
}
