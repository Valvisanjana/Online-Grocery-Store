package com.project.GloceryApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.GloceryApp.Service.CategoryService;
import com.project.GloceryApp.dto.CategoryDto;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/add")
	public CategoryDto addCategory(@RequestBody CategoryDto categoryDto) {
		return categoryService.addCategory(categoryDto);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/get/category/{id}")
	public CategoryDto getCategoryById(@PathVariable int id) {
		return categoryService.getCatgoryById(id);
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/get/{name}")
	public CategoryDto getCategoryByName(@PathVariable String name) {
		return categoryService.getCatgoryByName(name);
	}

	@PreAuthorize("hasAnyRole('ADMIN','USER')")
	@GetMapping("/getAll")
	public List<CategoryDto> getCategories() {
		return categoryService.getAll();
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/edit/Category/{id}")
	public CategoryDto getupdateById(@RequestBody CategoryDto categoryDto, @PathVariable int id) {
		return categoryService.getUpdateCatgoryById(categoryDto, id);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("delete/{id}")
	public String deleteById(@PathVariable int id) {
		categoryService.getDeleteCatgoryById(id);
		return "category deleted";
	}
}
