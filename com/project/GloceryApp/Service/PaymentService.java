package com.project.GloceryApp.Service;

import java.util.List;

import com.project.GloceryApp.dto.PaymentDto;

public interface PaymentService {

	PaymentDto makePayment(PaymentDto paymentDto);
	
	List<PaymentDto> getAllPayments();
	
	PaymentDto getPaymentById(int id, int orderId);
	
    void deletePaymentById(int id);	
}
 