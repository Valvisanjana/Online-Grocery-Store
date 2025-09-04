package com.project.GloceryApp.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
	private int id;
	private String paymentMethod;
	private LocalDateTime paymentDate;
	private double amountPaid;
	private String paymentStatus;
	private int orderId;

}
