package com.project.GloceryApp.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.GloceryApp.Repository.OrderRepository;
import com.project.GloceryApp.Service.OrderService;
import com.project.GloceryApp.dto.OrderDto;
import com.project.GloceryApp.entity.Order;
import com.project.GloceryApp.exception.OrderNotFoundException;
import com.project.GloceryApp.exception.UserNotFoundException;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private ModelMapper modelMap;

	private OrderDto convertToDto(Order order) {
		return modelMap.map(order, OrderDto.class);
	}

	private Order convertToEntity(OrderDto orderDto) {
		return modelMap.map(orderDto, Order.class);
	}

	@Override
	public OrderDto createOrder(OrderDto orderDto) {
		Order order = convertToEntity(orderDto);
		Order saveorder = orderRepo.save(order);
		return convertToDto(saveorder);
	}

	@Override
	public OrderDto getOrderById(int orderId) {
		Order order = orderRepo.findById(orderId)
				.orElseThrow(() -> new OrderNotFoundException("Order Not Found with id:" + orderId));
		return convertToDto(order);
	}

	@Override
	public OrderDto getOrdersByUserId(int userId) {
		Order order = orderRepo.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User Not Found with id:" + userId));
		return convertToDto(order);
	}

	@Override
	public List<OrderDto> getAllOrders() {
		List<Order> order = orderRepo.findAll();
		return order.stream().map(Order -> modelMap.map(Order, OrderDto.class)).collect(Collectors.toList());
	}

	@Override
	public void deleteOrder(int orderId) {
		orderRepo.deleteById(orderId);

	}

	@Override
	public OrderDto editOrderById(OrderDto orderDto, int orderId) {
		Order order = orderRepo.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));

		if (orderDto.getStatus() != null) {
			order.setStatus(orderDto.getStatus());
		}

		if (orderDto.getDeliveryAddress() != null) {
			order.setDeliveryAddress(orderDto.getDeliveryAddress());
		}

		Order updateOrder = orderRepo.save(order);
		return convertToDto(updateOrder);
	}
}
