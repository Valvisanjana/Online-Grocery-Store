package com.project.GloceryApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.GloceryApp.Service.OrderService;
import com.project.GloceryApp.dto.OrderDto;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PreAuthorize("hasRole('USER')")
	@PostMapping("/order")
	public OrderDto doOrder(@RequestBody OrderDto orderDto) {
		OrderDto addOrder = orderService.createOrder(orderDto);
		return orderService.createOrder(orderDto);
	}

	@GetMapping("/getOrder/{orderId}")
	public OrderDto getOrderById(@PathVariable int orderId) {
		return orderService.getOrderById(orderId);
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/getbyUserId/{userId}")
	public OrderDto getOrderByUserId(@PathVariable int userId) {
		return orderService.getOrdersByUserId(userId);
	}

	@GetMapping("/get/Orders")
	public List<OrderDto> getOrders() {
		return orderService.getAllOrders();
	}

	@PutMapping("/update/Order/{orderId}")
	public OrderDto updateOrder(@PathVariable int OId, @RequestBody OrderDto orderDto) {
		return orderService.editOrderById(orderDto, OId);
	}

	@PreAuthorize("hasRole('USER')")
	@DeleteMapping("/delete/Order/{orderId}")
	public String deleteOrder(@PathVariable int orderId) {
		orderService.deleteOrder(orderId);
		return "order canceled successfully!";
	}
}
