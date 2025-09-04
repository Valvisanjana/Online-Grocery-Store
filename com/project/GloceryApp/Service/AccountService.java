package com.project.GloceryApp.Service;

import com.project.GloceryApp.dto.AccountDto;

public interface AccountService {

	AccountDto createAccount(AccountDto accountDto, int userId);

	AccountDto updateAccount(int userId, AccountDto accountDto);

	void deleteAccount(int id);

	AccountDto getAccByEmailAndphone(String email, String phone);
}
