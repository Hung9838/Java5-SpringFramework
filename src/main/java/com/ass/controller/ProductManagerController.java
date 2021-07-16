package com.ass.controller;

import java.util.Date;
import java.util.List;
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

import com.ass.dao.CategoryDao;
import com.ass.dao.ProductDao;
import com.ass.model.Category;
import com.ass.model.Product;

@Controller
@RequestMapping("/admin/product/crud")
public class ProductManagerController {
	@Autowired 
	CategoryDao categoryDao;
	
	@Autowired
	ProductDao productDao;
	
	@PostMapping("/add")
	public String add(
			Model model,
			@RequestParam(name = "id", defaultValue = "-1") Integer id,
			@RequestParam("name") String name,
			@RequestParam("describe") String describe,
			@RequestParam(name = "availabe", defaultValue = "false") String[] checkbox,
			@RequestParam(name = "promotions", defaultValue = "0") Double promotions,
			@RequestParam("categoryName") Optional<String> select,
			@RequestParam(name = "price", defaultValue = "0") Double price,
			@RequestParam("page") Integer page)
	{	
		if (name.equals("")) {;
			return "redirect:/admin/product/page?p="+page;
		}
		if (promotions > 100 || promotions < 0) {;
			return "redirect:/admin/product/page?p="+page;
		}
		Product pt = new Product();
		if (id != -1) {
			pt.setId(id);
		}
		pt.setName(name);
		pt.setImage("2.jpg");
		pt.setDescribe(describe);
		Date d = new Date();
		pt.setCreateDate(d);
		boolean availabe = checkbox[0].equals("false")? false:true;
		pt.setAvaliabe(availabe);
		pt.setPromotions(promotions);
		String categoryName = select.get();
		pt.setCategory(categoryDao.findById(categoryName).get());
		pt.setPrice(price);
		
		try {
			productDao.save(pt);
		} catch (Exception e) {
			model.addAttribute("loi", "Bạn phải nhập đầy đủ thông tin và đúng định dạng");
		}
		
		return "redirect:/admin/product/page?p="+page;
	}
		
	@GetMapping("/edit")
	public String edit(Model model,
			@RequestParam("id") Integer id,
			@RequestParam("page") Integer page) {
		Product pt = productDao.findById(id).get();
		model.addAttribute("ptForm", pt);
		return "forward:/admin/product/page?p="+page;
	}
		
	@GetMapping("/delete")
	public String delete(
			Model model, 
			@RequestParam("id") Integer id,
			@RequestParam("page") Integer page) {
		try {
			productDao.deleteById(id);
		} catch (Exception e) {
			model.addAttribute("loi", "Sản phẩm này đang có đơn hàng không xóa được");
		}
		return "forward://admin/product/page?p="+page;
	}
	
	@GetMapping("/sapXepTen")
	public String SapXepTen(Model model,
			@RequestParam("p") Optional<Integer> p) {
		Sort sort = Sort.by(Direction.DESC, "name");
		Pageable pageable = PageRequest.of(p.orElse(0), 3, sort);
		
		List<Category> listSelectCT = categoryDao.findAll();
		model.addAttribute("listSelectCT", listSelectCT);
		
		Page<Product> page = productDao.findAll(pageable);
		model.addAttribute("pagePT", page);			
		model.addAttribute("view", "/views/admin/product.jsp");
		return "admin/home";
	}
	
	@GetMapping("/sapXepGiamGia")
	public String SapXepGiamGIa(Model model,
			@RequestParam("p") Optional<Integer> p) {
		Sort sort = Sort.by(Direction.DESC, "promotions");
		Pageable pageable = PageRequest.of(p.orElse(0), 3, sort);
		
		List<Category> listSelectCT = categoryDao.findAll();
		model.addAttribute("listSelectCT", listSelectCT);
		
		Page<Product> page = productDao.findAll(pageable);
		model.addAttribute("pagePT", page);			
		model.addAttribute("view", "/views/admin/product.jsp");
		return "admin/home";
	}
}
