package com.ass.controller;

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
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.ass.dao.CategoryDao;
import com.ass.model.Category;

@Controller
@RequestMapping("/admin/category/crud")
public class CategoryManagerController {
	@Autowired
	CategoryDao categoryDao;
	
	@PostMapping("/add")
	public String add(@RequestParam("name") String name, Model model,
			@RequestParam("page") Integer page,
			@RequestParam(name ="active",defaultValue = "false") String[] checkbox) {
		if (name.equals("")) {
			return "redirect:/admin/category/page?p="+page;
		}
		boolean active = checkbox[0].equals("false")? false:true;
		Category ct = new Category();
		ct.setName(name);
		ct.setActive(active);
		categoryDao.save(ct);
		return "redirect:/admin/category/page?p="+page;
	}
	
	@GetMapping("/edit")
	public String edit(Model model, 
			@RequestParam("name") String name,
			@RequestParam("page") Integer page) {
		Category ct = categoryDao.findById(name).get();
		model.addAttribute("ctForm", ct);
		return "forward:/admin/category/page?p="+page;
	}
	
	@GetMapping("/delete")
	public String delete(
			Model model,
			@RequestParam("name") String name,
			@RequestParam("page") Integer page) {
			try {
				categoryDao.deleteById(name);
			} catch (Exception e) {
				model.addAttribute("loi", "Danh mục này đang có sản phẩm không xóa được");
			}
		return "forward://admin/category/page?p="+page;
	}
	
	@GetMapping("/sapXep")
	public String sapXepDanhMucTheoTen (Model model,
			@RequestParam("p") Optional<Integer> p) {
		Sort sort = Sort.by(Direction.DESC, "name");
		Pageable pageable = PageRequest.of(p.orElse(0), 3, sort);
		Page<Category> page = categoryDao.findAll(pageable);
		model.addAttribute("page", page);			
		model.addAttribute("view", "/views/admin/category.jsp");
		return "admin/home";
	}
}
