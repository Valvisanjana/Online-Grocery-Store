package com.project.GloceryApp.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.GloceryApp.Repository.ProductRepository;
import com.project.GloceryApp.Service.ProductService;
import com.project.GloceryApp.dto.ProductDto;
import com.project.GloceryApp.entity.Product;
import com.project.GloceryApp.exception.ProductNotFoundException;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private ModelMapper modelMap;

	private Product convertToEntity(ProductDto productDto) {
		return modelMap.map(productDto, Product.class);
	}

	private ProductDto convertToDto(Product product) {
		return modelMap.map(product, ProductDto.class);
	}

	@Override
	public ProductDto addProduct(ProductDto productDto) {
		Product product = convertToEntity(productDto);
		Product addProduct = productRepo.save(product);
		return convertToDto(addProduct);
	}

	@Override
	public ProductDto getProductById(int productId) {
		Product product = productRepo.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException("Product Not Found"));
		return convertToDto(product);
	}

	@Override
	public List<ProductDto> getProductByName(String name) {
		List<Product> product = productRepo.findByName(name);
		return product.stream().map(p -> convertToDto(p)).collect(Collectors.toList());
	}

	@Override
	public ProductDto UpdateProduct(ProductDto productDto, int productId) {
		Product ExsistingProduct = productRepo.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException("Product Not Found"));
		ExsistingProduct.setProductId(productDto.getProductId());
		ExsistingProduct.setName(productDto.getName());
		ExsistingProduct.setPrice(productDto.getPrice());
		ExsistingProduct.setDescription(productDto.getDescription());
		ExsistingProduct.setImageurl(productDto.getImageurl());
		Product product = productRepo.save(ExsistingProduct);
		return convertToDto(product);
	}

	@Override
	public String deleteProductById(int productId) {
		if (!productRepo.existsById(productId)) {
			throw new ProductNotFoundException("Product not exists with id:" + productId);
		}
		productRepo.deleteById(productId);
		return "product deleted!";
	}

	@Override
	public List<ProductDto> getProductByCategory(String name) {
		List<Product> product = productRepo.findByName(name);
		return product.stream().map(plist -> convertToDto(plist)).toList();
	}

	@Override
	public List<ProductDto> getProductByPriceRange(Double min, Double max) {
		List<Product> product = productRepo.findByPriceBetween(min, max);
		return product.stream().map(plist -> convertToDto(plist)).toList();
	}

	@Override
	public List<ProductDto> getProductByPriceRangeWithCategory(Double min, Double max, String name) {
		List<Product> product = productRepo.findByCategoryBetween(min, max, name);
		return product.stream().map(plist -> convertToDto(plist)).toList();
	}

	@Override
	public List<ProductDto> getProductByNameContaining(String keyword) {
		List<Product> products = productRepo.findByNameContaining(keyword);
		return products.stream().map(this::convertToDto).toList();
	}

	@Override
	public List<ProductDto> getProductSortedByPriceASC() {
		List<Product> product = productRepo.findAllByOrderByPriceAsc();
		return product.stream().map(this::convertToDto).toList();
	}

	@Override
	public List<ProductDto> getProductSortedByPriceDesc() {
		List<Product> products = productRepo.findAllByOrderByPriceDesc();
		return products.stream().map(this::convertToDto).toList();
	}

	@Override
	public List<ProductDto> getAllProducts() {
		List<Product> plist = productRepo.findAll();
		return plist.stream().map(this::convertToDto).collect(Collectors.toList());
	}

}
