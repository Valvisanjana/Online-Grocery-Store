package com.project.GloceryApp.ServiceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.GloceryApp.Repository.CategoryRepository;
import com.project.GloceryApp.Service.CategoryService;
import com.project.GloceryApp.dto.CategoryDto;
import com.project.GloceryApp.entity.Category;
import com.project.GloceryApp.exception.CategoryNotFoundException;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private ModelMapper modelMap;

	private Category convertToEntity(CategoryDto categoryDto) {
		return modelMap.map(categoryDto, Category.class);
	}

	private CategoryDto convertToDto(Category category) {
		return modelMap.map(category, CategoryDto.class);
	}

	@Override
	public CategoryDto addCategory(CategoryDto categoryDto) {
		Category category = convertToEntity(categoryDto);
		Category saveCategory = categoryRepo.save(category);
		return convertToDto(saveCategory);
	}

	@Override
	public CategoryDto getCatgoryById(int id) {
		Category category = categoryRepo.findById(id)
				.orElseThrow(() -> new CategoryNotFoundException("category not found"));
		return convertToDto(category);
	}

	@Override
	public CategoryDto getCatgoryByName(String name) {
		Category cate = categoryRepo.findByName(name);
		return convertToDto(cate);
	}

	@Override
	public List<CategoryDto> getAll() {
		List<Category> clist = categoryRepo.findAll();
		return clist.stream().map(this::convertToDto).toList();
	}

	@Override
	public CategoryDto getUpdateCatgoryById(CategoryDto categoryDto, int id) {
		Category ExistingCategory = categoryRepo.findById(id)
				.orElseThrow(() -> new CategoryNotFoundException("category not Found"));
		ExistingCategory.setName(categoryDto.getName());
		Category updatedCategory = categoryRepo.save(ExistingCategory);
		return convertToDto(updatedCategory);
	}

	@Override
	public void getDeleteCatgoryById(int id) {
		categoryRepo.deleteById(id);
	}

}
