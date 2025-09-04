package com.project.GloceryApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.GloceryApp.Service.AccountService;
import com.project.GloceryApp.dto.AccountDto;

@RestController
@RequestMapping("/api/account")
public class AccountController {

	@Autowired
	private AccountService AccountService;

	@PostMapping("/create")
	public AccountDto CreateAccount(@RequestBody AccountDto accountDto, @RequestParam int userId) {
		return AccountService.createAccount(accountDto, userId);
	}

	@GetMapping("/get/{email}/{phone}")
	public AccountDto getAccount(@PathVariable String email, @PathVariable String phone) {
		return AccountService.getAccByEmailAndphone(email, phone);
	}

	@PutMapping("/edit/{id}")
	public AccountDto editAcc(@RequestBody AccountDto accountDto, @PathVariable("id") int userId) {
		return AccountService.updateAccount(userId, accountDto);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteAcc(@PathVariable int id) {
		AccountService.deleteAccount(id);
		return "Account is Deleted successfully!";
	}
}
