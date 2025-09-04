package com.project.GloceryApp.Service;

import java.util.List;

import com.project.GloceryApp.dto.AddressDto;

public interface AddressService {

	AddressDto AddAddress(AddressDto addressDto);

	AddressDto getByUId(int userId);

	AddressDto getById(int id);

	List<AddressDto> getAll();

	AddressDto updatebyId(AddressDto addressDto, int id);

	String deleteAddress(int id);

}
