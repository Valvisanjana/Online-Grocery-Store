package com.project.GloceryApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.GloceryApp.Service.PaymentService;
import com.project.GloceryApp.dto.PaymentDto;

@RestController
@RequestMapping("/api/Payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PreAuthorize("hasRole('USER')")
	@PostMapping("/make/Payment")
	public PaymentDto makePay(@RequestBody PaymentDto paymentDto) {
		return paymentService.makePayment(paymentDto);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/get/payment/{id}/{Oid}")
	public PaymentDto getById(@PathVariable int id, @PathVariable("Oid") int orderId) {
		return paymentService.getPaymentById(id, orderId);
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/getAll")
	public List<PaymentDto> getAll() {
		return paymentService.getAllPayments();
	}

	@PreAuthorize("hasRole('USER')")
	@DeleteMapping("delete/{id}")
	public void DeletePayment(@PathVariable int id) {
		paymentService.deletePaymentById(id);
	}
}
