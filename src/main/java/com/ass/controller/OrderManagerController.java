package com.ass.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ass.dao.AccountDao;
import com.ass.dao.OrderDao;
import com.ass.model.Order;
import com.ass.model.OrderDetail;

@Controller
@RequestMapping("/admin/order/crud")
public class OrderManagerController {
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	AccountDao accountDao;
	
	@PostMapping("/add")
	public String add(Model model,
			@RequestParam(name = "id", defaultValue = "-1") Integer id,
			@RequestParam("account") String account,
			@RequestParam("address") String address,
			@RequestParam("status") Optional<String> select,
			@RequestParam("page") Integer page) {
		if (address.equals("")) {
			return "redirect:/admin/order/page?p="+page;
		}
		Order or = new Order();
		if (id != -1) {
			or.setId(id);
		}
		try {
		or.setAccount(accountDao.findById(account).get());
			Date d = new Date();
			or.setCreateDate(d);
			or.setAddress(address);
			String status = select.get();
			or.setStatus(status);
			orderDao.save(or);
		} catch (Exception e) {

		}
		return "redirect:/admin/order/page?p="+page;
	}
	
	@GetMapping("/edit")
	public String edit(Model model,
			@RequestParam("id") Integer id,
			@RequestParam("page") Integer page) {
		Order or = orderDao.findById(id).get();
		model.addAttribute("orForm", or);
		return "forward:/admin/order/page?p="+page;
	}
	
	@GetMapping("/delete")
	public String delete(Model model,
			@RequestParam("id") Integer id,
			@RequestParam("page") Integer page) {
		try {
			orderDao.deleteById(id);
		} catch (Exception e) {
			model.addAttribute("loi", "Hóa đơn này đang có thông tin nên không xóa được");
		}
		return "forward://admin/order/page?p="+page;
	}
	
	@GetMapping("/sapXepTheoTaiKhoan")
	public String sapXepTheoTaiKhoan(Model model,
			@RequestParam("p") Optional<Integer> p) {
		Sort sort = Sort.by(Direction.DESC, "account");
		Pageable pageable = PageRequest.of(p.orElse(0), 3, sort);
		Page<Order> page = orderDao.findAll(pageable);
		model.addAttribute("pageOR", page);
		String[] orListStatus = {"Chờ xác nhận", "Chờ lấy hàng", "Đang giao", "Đã giao", "Đã hủy"};
		model.addAttribute("orListStatus", orListStatus);
		model.addAttribute("view", "/views/admin/order.jsp");
		return "admin/home";
	}
	
	@GetMapping("/sapXepTheoNgay")
	public String sapXepTheoNgay(Model model,
			@RequestParam("p") Optional<Integer> p) {
		Sort sort = Sort.by(Direction.DESC, "createDate");
		Pageable pageable = PageRequest.of(p.orElse(0), 3, sort);
		Page<Order> page = orderDao.findAll(pageable);
		model.addAttribute("pageOR", page);
		String[] orListStatus = {"Chờ xác nhận", "Chờ lấy hàng", "Đang giao", "Đã giao", "Đã hủy"};
		model.addAttribute("orListStatus", orListStatus);
		model.addAttribute("view", "/views/admin/order.jsp");
		return "admin/home";
	}
}
