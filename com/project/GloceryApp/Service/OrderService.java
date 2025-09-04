package com.project.GloceryApp.Service;

import java.util.List;

import com.project.GloceryApp.dto.OrderDto;

public interface OrderService {

	OrderDto createOrder(OrderDto orderDto);

	OrderDto getOrderById(int orderId);

	OrderDto getOrdersByUserId(int userId);

	List<OrderDto> getAllOrders();

	OrderDto editOrderById(OrderDto orderDto, int orderId);

	void deleteOrder(int orderId);

}
