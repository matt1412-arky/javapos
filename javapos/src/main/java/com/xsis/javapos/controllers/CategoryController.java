package com.xsis.javapos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/category")
public class CategoryController {
	@GetMapping("")
	ModelAndView Index() {
		return new ModelAndView("/category/index");
	}
	
	@GetMapping("/{id}")
	ModelAndView Detail(@PathVariable int id) {
		return new ModelAndView("/category/detail");
	}
}