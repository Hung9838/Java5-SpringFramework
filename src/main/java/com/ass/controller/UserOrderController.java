package com.ass.controller;

import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ass.dao.OrderDao;
import com.ass.dao.OrderDetailDao;
import com.ass.dao.ProductDao;
import com.ass.model.Order;
import com.ass.model.OrderDetail;
import com.ass.service.CartItemModel;
import com.ass.service.MailerService;
import com.ass.service.SessionService;
import com.ass.service.ShopingCartService;

@RequestMapping("user")
@Controller
public class UserOrderController {
	
	@Autowired
	ProductDao productDao;
	
	@Autowired 
	OrderDao orderDao;
	
	@Autowired
	OrderDetailDao orderDetailDao;
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	ShopingCartService shoppingCart;
	
	@Autowired
	MailerService mailService;
	
	@PostMapping("order")
	public String order(			
			@RequestParam("emailInput") String email,
			@RequestParam("addressInput") String address) throws MessagingException {
		
			Order or = new Order();
			or.setAccount(sessionService.getAttribute("account"));
			Date d = new Date();
			or.setCreateDate(d);
			or.setAddress(address);
			or.setStatus("Chờ xác nhận");
			
			orderDao.save(or);
			
			List<CartItemModel> list = shoppingCart.getList();
			
			String donHang = "";
			double tongGia = 0;
			
			for (CartItemModel item : list) {
				OrderDetail ordt = new OrderDetail();
				ordt.setOrder(or);
				ordt.setProduct(productDao.findById(item.getId()).get());
				ordt.setPrice(item.getPrice());
				ordt.setQuantity(item.getQty());
				
				orderDetailDao.save(ordt);
				
				donHang += "- Mã hóa đơn: "+or.getId()+"- Mặt hàng: "+"\n"+productDao.findById(item.getId()).get().getName()+"\n"
						+ "-số lượng: "+item.getQty()+"\n"+"-Giá: "+item.getPrice()+"\n";
				tongGia += item.getPrice();
			}
			donHang += "Tổng giá: "+tongGia;
			
			
			mailService.push(email,"Đơn hàng của bạn",donHang);
		
		return "redirect:/user";
	}
}
