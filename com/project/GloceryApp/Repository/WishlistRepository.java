package com.project.GloceryApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.GloceryApp.entity.Wishlist;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {

	boolean existsByUser_UserIdAndProduct_ProductId(int userId, int productId);

	Wishlist deleteByUser_UserIdAndProduct_ProductId(int userId, int productId);

	List<Wishlist> findByUser_UserId(int userId);

}
