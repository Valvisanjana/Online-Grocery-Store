package com.project.GloceryApp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressDto {

	@Min(value = 1, message = "Address ID must be valid")
	private int id;
	@NotBlank(message = "street must not be blank")
	private String street;
	@NotBlank(message = "city must not be blank")
	private String city;
	@NotBlank(message = "state must not be blank")
	private String state;
	@NotBlank(message = " pincode is reuqired")
	private String pincode;
}
