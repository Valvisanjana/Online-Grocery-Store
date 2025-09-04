package com.project.GloceryApp.ServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.GloceryApp.Repository.OrderRepository;
import com.project.GloceryApp.Repository.PaymentRepository;
import com.project.GloceryApp.Service.PaymentService;
import com.project.GloceryApp.dto.PaymentDto;
import com.project.GloceryApp.entity.Order;
import com.project.GloceryApp.entity.Payment;
import com.project.GloceryApp.exception.OrderNotFoundException;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepo;

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private ModelMapper modelMap;

	@Transactional
	@Override
	public PaymentDto makePayment(PaymentDto paymentDto) {

		Payment payment = new Payment();
		payment.setAmountPaid(paymentDto.getAmountPaid());
		payment.setPaymentMethod(paymentDto.getPaymentMethod());
		payment.setPaymentDate(LocalDateTime.now());
		payment.setPaymentStatus("successfull payment");

		com.project.GloceryApp.entity.Order order = orderRepo.findById(paymentDto.getOrderId())
				.orElseThrow(() -> new OrderNotFoundException("order not found"));

		payment.setOrder(order);

		Payment savePayment = paymentRepo.save(payment);

		return modelMap.map(savePayment, PaymentDto.class);
	}

	@Override
	public List<PaymentDto> getAllPayments() {
		List<Payment> payments = paymentRepo.findAll();
		return payments.stream().map(p -> modelMap.map(payments, PaymentDto.class)).collect(Collectors.toList());
	}

	@Override
	public PaymentDto getPaymentById(int id, int orderId) {
		Order order = orderRepo.findById(orderId)
				.orElseThrow(() -> new OrderNotFoundException("order not found with id:" + orderId));

		Payment payment = paymentRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Payment Not Found with id:" + id));

		return modelMap.map(payment, PaymentDto.class);
	}

	@Override
	public void deletePaymentById(int id) {
		paymentRepo.deleteById(id);
	}

}
