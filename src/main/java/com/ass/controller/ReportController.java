package com.ass.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ass.dao.OrderDetailDao;
import com.ass.modelQuery.CategoryReport;
import com.ass.modelQuery.ParamTimeReport;
import com.ass.modelQuery.ProductReport;
import com.ass.modelQuery.TimeReport;

@Controller
@RequestMapping("/admin")
public class ReportController {
	@Autowired
	OrderDetailDao orderDetailDao;
	
	public void loadCategoryReport(Model model) {
		model.addAttribute("categoryReport", "/views/admin/categoryReport.jsp");
	}
	
	public void loadProductReport(Model model) {
		model.addAttribute("productReport", "/views/admin/productReport.jsp");
	}
	
	public void loadTimeReport(Model model) {
		model.addAttribute("timeReport", "/views/admin/timeReport.jsp");
	}
	
	@GetMapping("/report")
	public String name(Model model,
			@RequestParam("pct") Optional<Integer> pct,
			@RequestParam("ppr") Optional<Integer> ppr) {
		if (pct.orElse(0) == -1) {
			pct = Optional.of(0);
		}
		if (ppr.orElse(0) == -1) {
			ppr = Optional.of(0);
		}
		Pageable pageableCT = PageRequest.of(pct.orElse(0), 3);
		Page<CategoryReport> ctRP = orderDetailDao.FindCategoryReport(pageableCT);
		model.addAttribute("pageCategoryRP", ctRP);
		Pageable pageablePR = PageRequest.of(ppr.orElse(0), 3);
		Page<ProductReport> prRP = orderDetailDao.FindProductReport(pageablePR);
		model.addAttribute("pageProductRP", prRP);
		model.addAttribute("view", "/views/admin/reportViews.jsp");
				
		loadCategoryReport(model);
		loadProductReport(model);
		loadTimeReport(model);		
		return "admin/home";
	}
	
	@PostMapping("/TimeReport")
	public String date(Model model,
			@RequestParam("dateFrom") String from,
			@RequestParam("dateEnd") String end,
			@RequestParam("pct") Optional<Integer> pct,
			@RequestParam("ppr") Optional<Integer> ppr) throws ParseException {

//			System.out.println(tr.getDoanhThu());
			Date now = new Date();
			Date dateFrom = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(from+" "+now.getHours()+":"+now.getMinutes());	
			Date dateEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(end+" "+now.getHours()+":"+now.getMinutes());
			TimeReport tr = orderDetailDao.findTimeReport(dateFrom, dateEnd);
			model.addAttribute("soDonHang", tr.getSoDonHang());
			model.addAttribute("doanhThu", tr.getDoanhThu());
			
			// load two table
			
			if (pct.orElse(0) == -1) {
				pct = Optional.of(0);
			}
			if (ppr.orElse(0) == -1) {
				ppr = Optional.of(0);
			}
			Pageable pageableCT = PageRequest.of(pct.orElse(0), 3);
			Page<CategoryReport> ctRP = orderDetailDao.FindCategoryReport(pageableCT);
			model.addAttribute("pageCategoryRP", ctRP);
			Pageable pageablePR = PageRequest.of(ppr.orElse(0), 3);
			Page<ProductReport> prRP = orderDetailDao.FindProductReport(pageablePR);
			model.addAttribute("pageProductRP", prRP);
			model.addAttribute("view", "/views/admin/reportViews.jsp");
			loadCategoryReport(model);
			loadProductReport(model);
			loadTimeReport(model);
		return "admin/home";		
	}
}
