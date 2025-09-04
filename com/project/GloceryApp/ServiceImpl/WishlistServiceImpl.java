package com.project.GloceryApp.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.GloceryApp.Repository.ProductRepository;
import com.project.GloceryApp.Repository.UserRepository;
import com.project.GloceryApp.Repository.WishlistRepository;
import com.project.GloceryApp.Service.WishlistService;
import com.project.GloceryApp.dto.ProductDto;
import com.project.GloceryApp.dto.WishlistDto;
import com.project.GloceryApp.entity.Product;
import com.project.GloceryApp.entity.User;
import com.project.GloceryApp.entity.Wishlist;
import com.project.GloceryApp.exception.ProductNotFoundException;
import com.project.GloceryApp.exception.UserNotFoundException;

@Service
public class WishlistServiceImpl implements WishlistService {

	@Autowired
	private WishlistRepository wishlistRepo;

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ModelMapper modelMap;

	private Wishlist convertToEntity(WishlistDto wishlistDto) {
		return modelMap.map(wishlistDto, Wishlist.class);
	}

	private WishlistDto convertToDto(Wishlist wishlist) {
		return modelMap.map(wishlist, WishlistDto.class);
	}

	public Product convertToEntity(ProductDto productDto) {
		return modelMap.map(productDto, Product.class);
	}

	public ProductDto convertToDto(Product product) {
		return modelMap.map(product, ProductDto.class);
	}

	@Override
	public WishlistDto addWishlist(int userId, int productId) {

		if (wishlistRepo.existsByUser_UserIdAndProduct_ProductId(userId, productId)) {
			throw new RuntimeException("Already In wishlist");
		}

		Product product = productRepo.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException("Product not found"));

		User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User Not Found"));

//		to add
		Wishlist wishlist = new Wishlist();
		wishlist.setUser(user);
		wishlist.setProduct(product);

		Wishlist saveWishlist = wishlistRepo.save(wishlist);

		WishlistDto wishlistDto = convertToDto(saveWishlist);

		return wishlistDto;
	}

	@Override
	public void removeWishlist(int userId, int productId) {
		Wishlist wishlist = wishlistRepo.deleteByUser_UserIdAndProduct_ProductId(userId, productId);
		if (wishlist == null)
			throw new RuntimeException("Wishlist item not found");
	}

	@Override
	public List<WishlistDto> getUserWishList(int userId) {
		List<Wishlist> wishlists = wishlistRepo.findByUser_UserId(userId);

		return wishlists.stream().map(wlist -> {
			WishlistDto dto = new WishlistDto();
			dto.setId(wlist.getId());
			dto.setUserId(userId);
			dto.setProduct(modelMap.map(wlist.getProduct(), ProductDto.class));
			return dto;
		}).collect(Collectors.toList());
	}

}
