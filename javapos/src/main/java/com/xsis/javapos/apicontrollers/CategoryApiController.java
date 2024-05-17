package com.xsis.javapos.apicontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.javapos.models.Category;
import com.xsis.javapos.services.CategoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/category")
public class CategoryApiController {
	@Autowired
	private CategoryService categorySvc;
	
	@GetMapping("")
	public ResponseEntity<List<Category>> get() {
		try {
			List<Category> data = categorySvc.getAll();
		// List<Category> data = new ArrayList<Category>();

		// data.add(new Category());
		// data.get(data.size()-1).setId((long) 1);
		// data.get(data.size()-1).setName("Makanan");
		// data.get(data.size()-1).setDescription("Makanan dari API");

		// data.add(new Category());
		// data.get(data.size()-1).setId((long) 2);
		// data.get(data.size()-1).setName("Minuman");
		// data.get(data.size()-1).setDescription("Minuman dari API");

			return new ResponseEntity<List<Category>>(data, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
			// throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@PostMapping("")
	public void Create(@RequestBody final Category data) {
        //TODO: process POST request
		categorySvc.Create(data);
	}
}
