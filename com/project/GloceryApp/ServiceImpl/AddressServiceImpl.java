package com.project.GloceryApp.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.GloceryApp.Repository.AddressRepository;
import com.project.GloceryApp.Service.AddressService;
import com.project.GloceryApp.dto.AddressDto;
import com.project.GloceryApp.entity.Address;
import com.project.GloceryApp.exception.UserNotFoundException;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository AddRepo;

	@Autowired
	private ModelMapper modelMapper;

	private Address ConvertToEntity(AddressDto addressDto) {
		return modelMapper.map(addressDto, Address.class);
	}

	private AddressDto ConvertToDto(Address address) {
		return modelMapper.map(address, AddressDto.class);
	}

	@Override
	public AddressDto AddAddress(AddressDto addressDto) {
		Address address = ConvertToEntity(addressDto);
		Address saveAddress = AddRepo.save(address);
		return ConvertToDto(saveAddress);
	}

	@Override
	public AddressDto getById(int id) {
		Address address = AddRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Address Not found with id:" + id));
		return ConvertToDto(address);
	}

	@Override
	public AddressDto getByUId(int userId) {
		Address address = AddRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
		return ConvertToDto(address);
	}

	@Override
	public List<AddressDto> getAll() {
		List<Address> address = AddRepo.findAll();
		return address.stream().map(addr -> ConvertToDto(addr)).collect(Collectors.toList());
	}

	@Override
	public AddressDto updatebyId(AddressDto addressDto, int id) {
		Address ExistingAdd = AddRepo.findById(id).orElseThrow(() -> new RuntimeException("Address not Found"));
		ExistingAdd.setId(addressDto.getId());
		ExistingAdd.setStreet(addressDto.getStreet());
		ExistingAdd.setCity(addressDto.getCity());
		ExistingAdd.setPincode(addressDto.getPincode());
		ExistingAdd.setState(addressDto.getState());

		Address UpdatedAddress = AddRepo.save(ExistingAdd);
		return ConvertToDto(UpdatedAddress);
	}

	@Override
	public String deleteAddress(int id) {
		AddRepo.deleteById(id);
		return "Address Deleted";
	}

}
