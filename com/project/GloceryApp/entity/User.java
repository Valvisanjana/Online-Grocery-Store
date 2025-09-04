package com.project.GloceryApp.entity;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String userName;
	private String email;
	private String password;
	private String role;

	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private Set<Account> account;

	@OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
	private Cart cart;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<Order> orders;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<Wishlist> wishlists;

}
