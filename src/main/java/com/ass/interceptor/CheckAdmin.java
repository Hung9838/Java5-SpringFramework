package com.ass.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ass.model.Account;

@Component
public class CheckAdmin implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (request.getSession().getAttribute("account") != null) {
			Account user = (Account) request.getSession().getAttribute("account");
			return user.isRole() == true ? true : false;
		}
				
		return false;
	}
}
