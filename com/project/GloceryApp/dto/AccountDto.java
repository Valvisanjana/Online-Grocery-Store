package com.project.GloceryApp.dto;

import java.util.Set;

import com.project.GloceryApp.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountDto {
	private int id;
	private String fullName;
	private String email;
	private String phone;
	private Set<Address> address;
}
