package com.xsis.javapos.controllers;

// import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.xsis.javapos.models.Category;
import com.xsis.javapos.services.CategoryService;
import com.xsis.javapos.viewmodels.CategoryView;

import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Matthew
 */
@Controller
@RequestMapping("/category")
public class CategoryController {
	@Autowired 
	private CategoryService categorySvc;

	private final String apiUrl = "http://localhost:8080/api/category";
	private RestTemplate restTemplate = new RestTemplate();
	@GetMapping("")
	ModelAndView Index() throws Exception {
		ModelAndView view = new ModelAndView("category/index");
		try {
			ResponseEntity<CategoryView[]> apiResponse = restTemplate.getForEntity(apiUrl, CategoryView[].class);
			if (apiResponse.getStatusCode() == HttpStatus.OK) {
				CategoryView[] data = apiResponse.getBody();
				view.addObject("data", data);
			} else {
				// Handle error
				throw new Exception(apiResponse.getStatusCode().toString() + ":" + apiResponse.getBody());
			}
		} catch (Exception e) {
			// TODO: handle exception
			view.addObject("Error : ", e.getMessage());
		}
		// try {
		// 	Optional<List<Map<String, Object>>> data = categorySvc.getAll();
		// 	// data.add(new Category((long)1, "Makanan", "Makanan"));
		// 	// data.add(new Category((long)2, "Minuman", "Minuman"));
		// 	// data.add(new Category());
		// 	// data.get(data.size()-1).setId((long) 1);
		// 	// data.get(data.size()-1).setName("Makanan");
		// 	// data.get(data.size()-1).setDescription("Makanan");

		// 	// data.add(new Category());
		// 	// data.get(data.size()-1).setId((long) 2);
		// 	// data.get(data.size()-1).setName("Minuman");
		// 	// data.get(data.size()-1).setDescription("Minuman");
		// 	view.addObject("data", data.get());
		// } catch (Exception e) {
		// 	// TODO Auto-generated catch block
		// }
		return view;
	}
	
	@GetMapping("detail/{id}")
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

	@GetMapping("/edit/{id}")
    ModelAndView showEditForm(@PathVariable long id) throws Exception {
        ModelAndView view = new ModelAndView("category/edit");
		// ResponseEntity<CategoryView> apiResponse = restTemplete.getForEntity();
        try {
            Optional<Category> categoryOpt = categorySvc.getById(id);
            if (categoryOpt.isPresent()) {
                view.addObject("category", categoryOpt.get());
            } else {
                // Handle case where category is not found
                view.setViewName("redirect:/category");
            }
        } catch (Exception e) {
            // Handle exception
            throw e;
        }
        return view;
    }
	
	@PostMapping("edit/save")
	ModelAndView Edit (@ModelAttribute Category data) throws Exception {
		//TODO: process PUT request
		try {
			Category updateCategory = categorySvc.Update(data);

			if (updateCategory.getId() > 0) {
				System.out.println("New Category has been edited!");
			} else {
				System.out.println("Failed to update new Category");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e.getMessage());
		}
		// System.out.print(data);
		return new ModelAndView("redirect:/category");
	}

	@GetMapping("/delete/{id}")
	ModelAndView Delete(@PathVariable long id) throws Exception {
		ModelAndView view = new ModelAndView("category/delete");
		try {
			Optional<Category> categoryOpt = categorySvc.getById(id);
			if (categoryOpt.isPresent()) {
				view.addObject("category", categoryOpt.get());
			} else {
				// Handle case where category is not found
				view.setViewName("redirect:/category");
			}
		} catch (Exception e) {
			// Handle exception
			throw e;
		}
		return view;
	}

	@PostMapping("delete/save")
	ModelAndView DeleteSave(@ModelAttribute Category data) {
		try {
			// Perform deletion
			categorySvc.Delete(data.getId(), 1);
			
			System.out.println("Category has been deleted!");
		} catch (Exception e) {
			// Handle exception
			System.out.println("Error: " + e.getMessage());
		}
		
		// Redirect to the category index page
		return new ModelAndView("redirect:/category");
	}
}
