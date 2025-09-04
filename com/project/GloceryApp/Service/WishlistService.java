package com.project.GloceryApp.Service;

import java.util.List;

import com.project.GloceryApp.dto.WishlistDto;

public interface WishlistService {
      
	public WishlistDto addWishlist(int userId, int productId );
	
	void removeWishlist(int userId, int productId);
	
	List<WishlistDto> getUserWishList(int userId);
	
	
}
