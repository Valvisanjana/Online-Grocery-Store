package com.project.GloceryApp.Controller;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.GloceryApp.Repository.UserRepository;
import com.project.GloceryApp.dto.LoginRequest;
import com.project.GloceryApp.dto.RegisterRequest;
import com.project.GloceryApp.entity.User;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder passEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/register")
	public String register(@RequestBody RegisterRequest registerReq) {
		if (userRepo.findByEmail(registerReq.getEmail()) != null) {
			throw new RuntimeException("Email Already Exists!");
		}

		User user = new User();
		user.setUserName(registerReq.getUserName());
		user.setEmail(registerReq.getEmail());
		user.setPassword(passEncoder.encode(registerReq.getPassword()));

		if (registerReq.getRole() != null) {
			user.setRole(registerReq.getRole());
		} else {
			user.setRole("ROLE_USER");
		}

		userRepo.save(user);
		return "User registerd successfully";
	}

	@PostMapping("/login")
	public String login(@RequestBody LoginRequest loginReq) throws AuthenticationException {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginReq.getUserName(), loginReq.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return "User login successfully";
	}

}
