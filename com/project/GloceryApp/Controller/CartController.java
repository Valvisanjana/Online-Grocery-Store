package com.project.GloceryApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.GloceryApp.Service.CartService;
import com.project.GloceryApp.dto.CartDto;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@PreAuthorize("hasRole('USER')")
	@PostMapping("/add")
	public CartDto addProductToCart(@RequestParam int userId, @RequestParam int productId,
			@RequestParam double totalPrice) {
		return cartService.addProductToCart(userId, productId, totalPrice);
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/get/Item")
	public CartDto getCartItem(@RequestParam int userId) {
		return cartService.getCartByUserId(userId);
	}

	@PutMapping("/edit/CartItem")
	public CartDto updatedCart(@RequestParam int userId, @RequestParam int productId, @RequestParam double totalPrice) {
		return cartService.updateProductCart(userId, productId, totalPrice);
	}

	@PreAuthorize("hasRole('USER')")
	@DeleteMapping("/delete/Item")
	public void deleteProductFromC(@RequestParam int userId, @RequestParam int productId) {
		cartService.removeProductFromCart(userId, productId);
	}
}
