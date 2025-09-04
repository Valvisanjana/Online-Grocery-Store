package com.project.GloceryApp.dto;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

	@Min(value = 1, message = "ID must be valid")
	private int Cid;

	@Min(value = 1, message = "User Id must be valid")
	private int userId;

	@NotEmpty(message = "Cart must be contain Atleast one Item")
	private List<CartItemDto> items;

	private double totalPrice;
}
