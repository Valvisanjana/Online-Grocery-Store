package com.project.GloceryApp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {

	private int id;
	@Min(value = 1, message = "Product ID must be Valid")
	private int productId;

	@NotBlank(message = "product name is reuqired")
	private String productname;

	@Min(value = 1, message = "product quantity must be greater then 1")
	private int quantity;

	@Min(value = 1, message = "price must be greater then 1 or more")
	private double price;

	@NotBlank(message = "product Image is must")
	private String imageurl;

	@Min(value = 0, message = "price must be 0 or greater then 1")
	private double totalprice;

}
