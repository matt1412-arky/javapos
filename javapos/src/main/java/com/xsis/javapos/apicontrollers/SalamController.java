package com.xsis.javapos.apicontrollers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.javapos.models.Salam;

@RestController
@RequestMapping("api/Salam")
public class SalamController {
	@GetMapping("")
	public Salam SalamJuga(@RequestParam(value = "nama", defaultValue = "Orang") final String nama) {
		return new Salam (13 , nama);
	}
	
	@GetMapping("/{nama}")
	public String Salam(@PathVariable final String nama) {
		return "Selamat Datang, " + nama + "!";
	}
}
