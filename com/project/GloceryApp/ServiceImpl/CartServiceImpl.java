package com.project.GloceryApp.ServiceImpl;

import java.util.ArrayList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.GloceryApp.Repository.CartRepository;
import com.project.GloceryApp.Repository.ProductRepository;
import com.project.GloceryApp.Repository.UserRepository;
import com.project.GloceryApp.Service.CartService;
import com.project.GloceryApp.dto.CartDto;
import com.project.GloceryApp.entity.Cart;
import com.project.GloceryApp.entity.CartItem;
import com.project.GloceryApp.entity.Product;
import com.project.GloceryApp.entity.User;
import com.project.GloceryApp.exception.ProductNotFoundException;
import com.project.GloceryApp.exception.UserNotFoundException;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private ModelMapper modelMap;

	private CartDto convertToDto(Cart cart) {
		return modelMap.map(cart, CartDto.class);
	}

	private Cart convertToEntity(CartDto cartDto) {
		return modelMap.map(cartDto, Cart.class);
	}

	@Override
	public CartDto addProductToCart(int userId, int productId, double totalPrice) {
		if (cartRepo.existsByUser_userIdAndProduct_productId(userId, productId)) {
			throw new RuntimeException("Product already added");
		}

		User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("user not found"));

		Product product = productRepo.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException("Prouct not found"));

		Cart cart = cartRepo.findByUser(user).orElse(null);
		if (cart == null) {
			Cart newCart = new Cart();
			newCart.setUser(user);
			newCart.setItems(new ArrayList<>());
			newCart.setTotalprice(0.0);
		}

		CartItem cartItem = new CartItem();
		cartItem.setProduct(product);
		cartItem.setQuantity(1);
		cartItem.setTotalprice(totalPrice);
		cartItem.setCart(cart);

		cart.getItems().add(cartItem);
		cart.setTotalprice(cart.getTotalprice() + totalPrice);

		Cart savedcart = cartRepo.save(cart);
		return convertToDto(savedcart);
	}

	@Override
	public void removeProductFromCart(int userId, int productId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("user not found"));

		Cart cart = cartRepo.findByUser(user);
		if (cart == null) {
			throw new RuntimeException("cart not found");
		}

		CartItem cartItemRemove = cart.getItems().stream()
				.filter(items -> items.getProduct().getProductId() == productId).findFirst()
				.orElseThrow(() -> new ProductNotFoundException("product not found"));

		cart.getItems().remove(cartItemRemove);
		cartRepo.save(cart);
	}

	@Override
	public CartDto updateProductCart(int userId, int productId, double totalPrice) {
		User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("user not found"));

		Cart cart = cartRepo.findByUser(user);
		if (cart == null) {
			throw new RuntimeException("cart not found");
		}
		boolean itemUpdate = false;
		for (CartItem item : cart.getItems()) {
			if (item.getProduct().getProductId() == productId) {
				item.setTotalprice(totalPrice);
				itemUpdate = true;
				break;
			}
		}
		cartRepo.save(cart);
		return convertToDto(cart);
	}

	@Override
	public CartDto getCartByUserId(int userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("user not found"));

		Cart cart = cartRepo.findByUser(user);

		if (cart == null) {
			throw new RuntimeException("cart not found");
		}
		return convertToDto(cart);
	}

}
