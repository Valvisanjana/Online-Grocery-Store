package com.project.GloceryApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.GloceryApp.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
