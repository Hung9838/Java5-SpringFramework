package com.ass.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ParamService {
	@Autowired
	HttpServletRequest request;
	
	public String getString(String name, String defaultValue) {
		String value = request.getParameter(name);
		return value != null ? value : defaultValue;
	}
	
	public int getInt(String name, int defaultValue) {
		String value = getString(name, String.valueOf(defaultValue));
		return Integer.parseInt(value);
	}
	
	public boolean getBoolean(String name, boolean defaultValue) {
		String value = getString(name, String.valueOf(defaultValue));
		return Boolean.parseBoolean(value);
	}
	
	public Date getDate(String name, String pattern) {
		String value = getString(name, "");
		try {
			return new SimpleDateFormat(pattern).parse(value);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Autowired
	ServletContext app;
	
	public File saveFile(MultipartFile file, String folder) {
		File dir = new File(app.getRealPath(folder)); //Lấy về đường dẫn vật lý của thư mục đó
		if (!dir.exists()) dir.mkdir(); //Nếu không tồn tại tạo thư mục đó
		try {
			File saveFile = new File(dir, file.getOriginalFilename()); //Lấy file từ phía client 
			file.transferTo(saveFile); //Copy file đấy vào trong đường dẫn vật lý 
			
			System.out.println("save tc: "+saveFile);
			return saveFile;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}
