package com.project.GloceryApp.Service;

import java.util.List;

import com.project.GloceryApp.dto.CategoryDto;

public interface CategoryService {

	CategoryDto addCategory(CategoryDto categoryDto);
	
	CategoryDto getCatgoryById(int id); 
	
	CategoryDto getCatgoryByName(String name);

	List<CategoryDto> getAll();
	
	CategoryDto getUpdateCatgoryById(CategoryDto categoryDto, int id);
	
	void getDeleteCatgoryById(int id);



}
