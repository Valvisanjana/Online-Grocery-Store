package com.project.GloceryApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.GloceryApp.entity.Cart;
import com.project.GloceryApp.entity.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

	Cart findByUser(User user);

	boolean existsByUser_userIdAndProduct_productId(int userId, int productId);

}
