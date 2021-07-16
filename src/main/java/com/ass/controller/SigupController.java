package com.ass.controller;

import java.util.Random;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ass.dao.AccountDao;
import com.ass.model.Account;
import com.ass.service.MailerService;

@Controller
public class SigupController {
	
	Account ac = new Account();
	
	@Autowired
	AccountDao accountDao;
	
	@Autowired
	MailerService mailService;
	
	int check = 0;
	
	@GetMapping("/emailChek")
	public String emailChek() throws MessagingException {
		Random rand = new Random();
		check = rand.nextInt(200);
		mailService.push(ac.getEmail(),"xác nhận tài khoản","Mã của bạn là: "+check);
		return "signUp/emailCheck";
	}
	
	@PostMapping("/create")
	public String create(
			@RequestParam Integer emailCheck) {
		if (check == emailCheck) {
			System.out.println("Thành công");
			try {
				accountDao.save(ac);
			} catch (Exception e) {
				System.out.println("Tài khoản nãy đã tồn tại");
			}
			return "login/login";
		}else {
			System.out.println("Thất bại");
			return "signUp/emailCheck";
		}

	}
	
	@PostMapping("/signUp")
	public String sigup(
			@RequestParam String username,
			@RequestParam String password,
			@RequestParam String email,
			@RequestParam String phoneNumber) {
		ac.setUsername(username);
		ac.setPassword(password);
		ac.setRole(false);
		ac.setFullname("N V A");
		ac.setPhoneNumber(phoneNumber);
		ac.setEmail(email);
		ac.setPhoneNumber(null);
		return "redirect:/emailChek";
	}
}
