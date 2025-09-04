package com.project.GloceryApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.GloceryApp.Service.AddressService;
import com.project.GloceryApp.dto.AddressDto;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@PreAuthorize("hasRole('USER')")
	@PostMapping("Add")
	public AddressDto AddAddress(@RequestBody AddressDto addressDto) {
		return addressService.AddAddress(addressDto);
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/getAdd/{id}")
	public AddressDto getById(@PathVariable int id) {
		return addressService.getById(id);
	}

	@GetMapping("/getAdd/{uId}")
	public AddressDto getByUId(@PathVariable int userId) {
		return addressService.getById(userId);
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/getAll")
	public List<AddressDto> getAllAddress() {
		return addressService.getAll();
	}

	@PreAuthorize("hasRole('USER')")
	@PutMapping("/update/Address")
	public AddressDto updateAddress(@RequestBody AddressDto addressDto, @PathVariable int id) {
		return addressService.updatebyId(addressDto, id);
	}

	@PreAuthorize("hasRole('USER')")
	@DeleteMapping("/remove/Address/{id}")
	public String deleteAddress(@PathVariable int id) {
		addressService.deleteAddress(id);
		return "address deleted!";
	}
}
