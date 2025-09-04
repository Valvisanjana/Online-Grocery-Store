package com.project.GloceryApp.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.GloceryApp.entity.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.GloceryApp.Repository.UserRepository;
import com.project.GloceryApp.exception.UserNotFoundException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepo.findByUserName(userName)
				.orElseThrow(() -> new UserNotFoundException("User not found with: " + userName));

		List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole()));

		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				authorities);

	} 

}
