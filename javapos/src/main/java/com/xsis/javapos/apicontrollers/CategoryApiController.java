package com.xsis.javapos.apicontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xsis.javapos.models.Category;
import com.xsis.javapos.services.CategoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("api/category")
public class CategoryApiController {
	@Autowired
	private CategoryService categorySvc;
	
	@GetMapping("")
	public ResponseEntity<?> get() {
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
			if (data.size() > 0 ) {
				return new ResponseEntity<List<Category>>(data, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			// TODO: handle exception
			// e.printStackTrace();
			// return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// @GetMapping("/getById/{id}")
	// public ResponseEntity<Category> getById(@PathVariable final long id) {
	// 	//TODO: process GET request
	// 	try {
	// 	Category data = categorySvc.getById();
	// 	return new ResponseEntity<Category>(data, HttpStatus.OK);
	// 	} catch (Exception e) {
	// 		// TODO: handle exception
	// 		e.printStackTrace();
	// 		return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
	// 		// throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
	// 	}
	// }

	@GetMapping("/getByName/{name}")
	public ResponseEntity<?> getByName(@PathVariable String name) {
		//TODO: process GET request
		try {
			List<Category> data = categorySvc.getByName(name);

			if (data.size() > 0) {
				return new ResponseEntity<List<Category>>(data, HttpStatus.OK);				
			} else {
				return new ResponseEntity<List<Category>>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("")
	public ResponseEntity<?> Create (@RequestBody final Category data) {
        //TODO: process POST request
		try {
			Category newCategory = categorySvc.Create(data);
			return new ResponseEntity<Category>(newCategory, HttpStatus.CREATED);
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("")
	public ResponseEntity<?> Update(@RequestBody final Category data) {
		//TODO: process PUT request
		try {
			Category updateCategory = categorySvc.Update(data);

			if (updateCategory.getId() > 0) {
				return new ResponseEntity<Category>(updateCategory, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Category does not exists!", HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{id}/{userId}")
	public ResponseEntity<?> Update(@PathVariable final long id, @PathVariable int userId) {
		//TODO: process DELETE request
		try {
			Category deletedCategory = categorySvc.Delete(id, userId);

			if (deletedCategory.getId() > 0) {
				return new ResponseEntity<Category>(deletedCategory, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Category does not exists!", HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
