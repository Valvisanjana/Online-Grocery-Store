package com.project.GloceryApp.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishlistDto {
	@Min(value = 1, message = "Id must be valid")
	private int Id;

	@Min(value = 1, message = "User ID must be valid")
	private int userId;

	private ProductDto product;
}
