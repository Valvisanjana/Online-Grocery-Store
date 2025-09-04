package com.project.GloceryApp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	private int userId;
	@NotNull(message = "Name is Required!")
	private String userName;
	private String email;
	@NotBlank(message = "role is required")
	private String role;

}
