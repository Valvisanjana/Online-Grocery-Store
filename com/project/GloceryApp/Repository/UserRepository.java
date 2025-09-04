package com.project.GloceryApp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.GloceryApp.dto.UserDto;
import com.project.GloceryApp.entity.User;

import jakarta.validation.Valid;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUserName(String userName);

	Optional<User> findByUserId(int userId);

	User findByEmail(String email);

	void save(@Valid UserDto userDto);

}
