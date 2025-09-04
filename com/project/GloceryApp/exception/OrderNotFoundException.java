package com.project.GloceryApp.exception;

public class OrderNotFoundException extends RuntimeException {
	public OrderNotFoundException(String message) {
		super(message);
	}

}
