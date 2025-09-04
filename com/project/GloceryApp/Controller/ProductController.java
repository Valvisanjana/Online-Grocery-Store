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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.GloceryApp.Service.ProductService;
import com.project.GloceryApp.dto.ProductDto;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/add") 
	public ProductDto addProduct(@RequestBody ProductDto productDto) { 
		return productService.addProduct(productDto);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/get/{id}")
	public ProductDto getProductById(@PathVariable("id") int productId) {
		return productService.getProductById(productId);
	}

	@GetMapping("/getByName/{name}")
	public List<ProductDto> getProductByName(@PathVariable String name) {
		return productService.getProductByName(name);
	}

	@GetMapping("/All/Product")
	public List<ProductDto> getProducts() {
		List<ProductDto> product = productService.getAllProducts();
		return product;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/edit/product/{id}")
	public ProductDto editProduct(@RequestBody ProductDto prouctDto, @PathVariable("id") int productId) {
		return productService.UpdateProduct(prouctDto, productId);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/remove/product/{id}")
	public String removeProduct(@PathVariable("id") int productId) {
		return productService.deleteProductById(productId);
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/getBy/Category/{name}")
	public List<ProductDto> getbycatg(@PathVariable String name) {
		return productService.getProductByCategory(name);
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/getBy/PriceRange")
	public List<ProductDto> getbyPriceRange(@RequestParam Double min, @RequestParam Double max) {
		return productService.getProductByPriceRange(min, max);
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/getBy/PriceRangeAndCategory")
	public List<ProductDto> getbyPriceRangeAndCategory(@RequestParam Double min, @RequestParam Double max,
			@RequestParam String name) {
		return productService.getProductByPriceRangeWithCategory(min, max, name);
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/getBy/NameContain/{name}")
	public List<ProductDto> getbyNameContain(@PathVariable String name) {
		return productService.getProductByNameContaining(name);
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/getBy/PriceAsc")
	public List<ProductDto> getbyPriceAsc() {
		return productService.getProductSortedByPriceASC();
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/getBy/PriceRangeDesc")
	public List<ProductDto> getbyPriceDesc() {
		return productService.getProductSortedByPriceDesc();
	}

}
