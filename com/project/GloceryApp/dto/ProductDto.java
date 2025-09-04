package com.project.GloceryApp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

	@Min(value = 1, message = "product Id must be greater then 1")
	private int productId;

	@NotBlank(message = "Product name is required")
	private String name;

	@NotBlank(message = "Product description is required")
	private String description;

	private boolean liked;

	@Min(value = 1, message = "price must be greater then 1 or more")
	private double price;

	@NotBlank(message = " product Image is must")
	private String imageurl;

}
