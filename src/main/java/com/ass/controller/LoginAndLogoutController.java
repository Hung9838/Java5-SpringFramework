package com.ass.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ass.dao.AccountDao;
import com.ass.model.Account;
import com.ass.service.CookieService;
import com.ass.service.SessionService;

@Controller
public class LoginAndLogoutController {
	@Autowired 
	AccountDao dao;
	
	@Autowired
	SessionService sessionService;
	
	@GetMapping("/login")
	public String login() {
		return "login/login";
	}
	
	@GetMapping("/logout")
	public String logout() {
		if (sessionService.getAttribute("account") != null) {
			sessionService.removeAttribute("account");
		}
		return "login/login";
	}
	
	@PostMapping("checkLogin")
	public String CheckLogin(Model model, 
		@RequestParam("username") String username,
		@RequestParam("password") String password
		) {
			
		try {
			Account user = dao.getById(username);
			if (password.equals(user.getPassword())) {
				System.out.println("ok");
				sessionService.setAttibute("account", user);
						
				return user.isRole() == true ? "redirect:/admin/category" : "redirect:/user";
			}else {
				System.out.println("tb");
				return "redirect:/login";
			}
		} catch (Exception e) {
			System.out.println("tai khoan nay khong ton tai");
			return "redirect:/login";
			
			
		}
		
	}
	
}
