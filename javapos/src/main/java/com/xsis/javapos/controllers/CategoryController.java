package com.xsis.javapos.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.javapos.models.Category;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Matthew
 */
@Controller
@RequestMapping("/category")
public class CategoryController {
	@GetMapping("")
	ModelAndView Index() {
		ModelAndView view = new ModelAndView("category/index");

		List<Category> data = new ArrayList<Category>();
		// data.add(new Category((long)1, "Makanan", "Makanan"));
		// data.add(new Category((long)2, "Minuman", "Minuman"));
		data.add(new Category());
		data.get(data.size()-1).setId((long) 1);
		data.get(data.size()-1).setName("Makanan");
		data.get(data.size()-1).setDescription("Makanan");

		data.add(new Category());
		data.get(data.size()-1).setId((long) 2);
		data.get(data.size()-1).setName("Minuman");
		data.get(data.size()-1).setDescription("Minuman");

		view.addObject("data", data);
		return view;
	}
	
	@GetMapping("/{id}")
	ModelAndView Details(@PathVariable int id) {
		return new ModelAndView("category/detail");
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
		// System.out.print(data);
		return new ModelAndView("redirect:/category");
	}
	
}
