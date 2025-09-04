package com.project.GloceryApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.GloceryApp.Service.WishlistService;
import com.project.GloceryApp.dto.WishlistDto;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

	@Autowired
	private WishlistService wishlistService;

	@PreAuthorize("hasRole('USER')")
	@PostMapping("/add")
	public WishlistDto saveWishlist(@RequestParam int userId, @RequestParam int productId) {
		return wishlistService.addWishlist(userId, productId);
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/Wishlist/get")
	public List<WishlistDto> getuserWishlist(@RequestParam int userId) {
		return wishlistService.getUserWishList(userId);
	}

	@PreAuthorize("hasRole('USER')")
	@DeleteMapping("/removeInwishlist")
	public void Deletewishlist(@RequestParam int userId, @RequestParam int productId) {
		wishlistService.removeWishlist(userId, productId);
	}
}
