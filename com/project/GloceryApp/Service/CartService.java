package com.project.GloceryApp.Service;

import org.springframework.stereotype.Service;

import com.project.GloceryApp.dto.CartDto;

@Service
public interface CartService {

	public CartDto addProductToCart(int userId, int productId, double totalPrice);

	public void removeProductFromCart(int userId, int productId);

	public CartDto updateProductCart(int userId, int productId, double totalPrice);

	public CartDto getCartByUserId(int userId);

}
