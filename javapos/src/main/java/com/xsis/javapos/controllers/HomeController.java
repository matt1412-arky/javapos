package com.xsis.javapos.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;

//import com.xsis.javapos.models.Salam;

@Controller
public class HomeController {
	@GetMapping("/")
	ModelAndView Index() {
		return new ModelAndView("/index");
	}
}
