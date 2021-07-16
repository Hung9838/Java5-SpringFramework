package com.ass.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ass.interceptor.CheckAdmin;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{
	@Autowired 
	CheckAdmin chekAdmin;
	
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(chekAdmin)
			.addPathPatterns("/admin/**");
	}
}
