package com.project.GloceryApp.Service;

import java.util.List;
import com.project.GloceryApp.dto.ProductDto;

public interface ProductService {

	ProductDto getProductById(int productId);

	List<ProductDto> getProductByName(String name);

	List<ProductDto> getAllProducts();

	ProductDto UpdateProduct(ProductDto productDto, int productId);

	String deleteProductById(int productId);

	List<ProductDto> getProductByCategory(String name);

	List<ProductDto> getProductByPriceRange(Double min, Double max);

	List<ProductDto> getProductByNameContaining(String keyword);

	List<ProductDto> getProductSortedByPriceASC();

	List<ProductDto> getProductSortedByPriceDesc();

	List<ProductDto> getProductByPriceRangeWithCategory(Double min, Double max, String name);

	ProductDto addProduct(ProductDto productDto);

}
