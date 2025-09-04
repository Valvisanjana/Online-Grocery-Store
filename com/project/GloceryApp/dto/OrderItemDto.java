package com.project.GloceryApp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
	@Min(value = 1, message = "Id Is required")
	private int id;

	@NotNull(message = "Product name is required")
	private String productname;

	@NotBlank(message = "Product description is required")
	private String description;

	@NotBlank(message = " product Image is must")
	private String imageurl;

	@Min(value = 1, message = "quantity must be 1 and greater then")
	private int quantity;

	@Min(value = 1, message = "price must be greater then 1")
	private double price;

}
