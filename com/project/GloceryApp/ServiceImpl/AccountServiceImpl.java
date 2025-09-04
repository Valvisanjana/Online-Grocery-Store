package com.project.GloceryApp.ServiceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.GloceryApp.Repository.AccountRepository;
import com.project.GloceryApp.Repository.UserRepository;
import com.project.GloceryApp.Service.AccountService;
import com.project.GloceryApp.dto.AccountDto;
import com.project.GloceryApp.entity.Account;
import com.project.GloceryApp.entity.User;
import com.project.GloceryApp.exception.UserNotFoundException;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository AccountRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ModelMapper modelMap;

	private AccountDto convertToDto(Account account) {
		return modelMap.map(account, AccountDto.class);
	}

	private Account convertToEntity(AccountDto accountDto) {
		return modelMap.map(accountDto, Account.class);
	}

	@Override
	public AccountDto createAccount(AccountDto accountDto, int userId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User Not Found with: " + userId));

		Account account = convertToEntity(accountDto);
		account.setUser(user);

		Account savedAcount = AccountRepo.save(account);
		return convertToDto(savedAcount);
	}

	@Override
	public AccountDto updateAccount(int userId, AccountDto accountDto) {
		User user = userRepo.findByUserId(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

		Account acc = AccountRepo.findById(accountDto.getId())
				.orElseThrow(() -> new RuntimeException("Account Not found"));

		acc.setFullName(accountDto.getFullName());
		acc.setEmail(accountDto.getEmail());
		acc.setAddress(accountDto.getAddress());
		acc.setPhone(accountDto.getPhone());

		Account newAcc = AccountRepo.save(acc);
		return convertToDto(newAcc);
	}

	@Override
	public void deleteAccount(int id) {
		AccountRepo.deleteById(id);
	}

	@Override
	public AccountDto getAccByEmailAndphone(String email, String phone) {
		Account account = AccountRepo.findByEmailAndPhone(email, phone);
		return convertToDto(account);
	}
}
