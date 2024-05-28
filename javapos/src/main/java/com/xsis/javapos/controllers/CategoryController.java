package com.xsis.javapos.controllers;

// import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.javapos.models.Category;
import com.xsis.javapos.services.CategoryService;

import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Matthew
 */
@Controller
@RequestMapping("/category")
public class CategoryController {
	@Autowired CategoryService categorySvc;
	@GetMapping("")
	ModelAndView Index() {
		ModelAndView view = new ModelAndView("category/index");

		try {
			List<Category> data = categorySvc.getAll();
			// data.add(new Category((long)1, "Makanan", "Makanan"));
			// data.add(new Category((long)2, "Minuman", "Minuman"));
			// data.add(new Category());
			// data.get(data.size()-1).setId((long) 1);
			// data.get(data.size()-1).setName("Makanan");
			// data.get(data.size()-1).setDescription("Makanan");

			// data.add(new Category());
			// data.get(data.size()-1).setId((long) 2);
			// data.get(data.size()-1).setName("Minuman");
			// data.get(data.size()-1).setDescription("Minuman");
			view.addObject("data", data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return view;
	}
	
	@GetMapping("/{id}")
	ModelAndView Details(@PathVariable long id) throws Exception {
		try {
			ModelAndView view = new ModelAndView("category/detail");
			List<Map<String, Object>> data = categorySvc.getByIdNative(id);
			if (data != null && !data.isEmpty()) {
				view.addObject("data", data.get(0));
				return view;
			} else {
				return new ModelAndView("redirect:/category");
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}

	@GetMapping("/add")
	ModelAndView Add() {
		ModelAndView view = new ModelAndView("category/add");
		Category category = new Category();

		view.addObject("category", category);
		return view;
	}

	@PostMapping("/save")
	ModelAndView Save(@ModelAttribute Category data) {
		try {
			Category newCategory = categorySvc.Create(data);

			if (newCategory.getId() > 0) {
				System.out.println("New Category has been saved!");
			} else {
				System.out.println("Failed to save new Category");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e.getMessage());
		}
		// System.out.print(data);
		return new ModelAndView("redirect:/category");
	}
	
}
