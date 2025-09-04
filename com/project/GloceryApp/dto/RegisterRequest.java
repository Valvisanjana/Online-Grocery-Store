package com.project.GloceryApp.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RegisterRequest {
	private String userName;
	private String email;
	private String password;
	private String role;
}
