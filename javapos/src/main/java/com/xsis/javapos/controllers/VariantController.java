package com.xsis.javapos.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.javapos.viewmodels.VariantView;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/variant")
public class VariantController {
	private final String apiUrl = "http://localhost:8080/api/variant";
	private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("")
	ModelAndView Index() {
		return new ModelAndView("/variant/index");
	}

	@PostMapping("/update")
	ResponseEntity<?> Update (VariantView data) {
		//TODO: process POST request
		ResponseEntity<?> apiResponse = null;

		try {
			restTemplate.put(apiUrl + "/", data);
			apiResponse = restTemplate.getForEntity(apiUrl + "/variant/getById/" + data.getId(), VariantView.class);
		} catch (Exception e) {
			// TODO: handle exception
			apiResponse = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return apiResponse;
	}

	// @GetMapping("/delete/{id}")
	// ModelAndView (@PathVariable String param) {
	// 	return new String();
	// }
	
	
}
