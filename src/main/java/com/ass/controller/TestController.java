package com.ass.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.ass.service.MailerService;

@Controller
public class TestController {
	@Autowired
	MailerService mailService;
	
	@GetMapping("mail")
	public String mail() throws MessagingException {
		mailService.push("viethungthomany@gmail.com","Subject 1","body 1");
		return "";
	}
}
