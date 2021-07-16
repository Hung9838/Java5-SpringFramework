package com.ass.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailModel {
	String form = "java5@gmail.com";
	String to;
	String subject;
	String body;
	List<String> cc = new ArrayList<>();
	List<String> bcc = new ArrayList<>();
	List<File> files = new ArrayList<>();
	
	public MailModel (String to,String subject, String body) {
		
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
	
	
}
