package com.project.GloceryApp.dto;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
	@Min(value = 1, message = " Order Id must be valid")
	private int orderId;

	@Min(value = 1, message = "User Id must be valid")
	private int userId;

	@NotBlank(message = "status must be required")
	private String status;

	private String deliveryAddress;
	@Min(value = 1, message = "amount must be 0 or more")
	private double totalAmount;
	private List<OrderItemDto> items;

}
