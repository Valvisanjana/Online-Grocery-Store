package com.project.GloceryApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.GloceryApp.dto.ProductDto;
import com.project.GloceryApp.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findByName(String name);

	List<Product> findByPriceBetween(Double min, Double max);

	@Query("SELECT p FROM Product p WHERE p.price BETWEEN :min AND :max AND p.category.name = :name ")
	List<Product> findByCategoryBetween(@Param("min") Double min, @Param("max") Double max,
			@Param("cate_name") String name);

	List<Product> findByNameContaining(String keyword);

	List<Product> findAllByOrderByPriceAsc();

	List<Product> findAllByOrderByPriceDesc();

	ProductDto save(ProductDto product);

}
