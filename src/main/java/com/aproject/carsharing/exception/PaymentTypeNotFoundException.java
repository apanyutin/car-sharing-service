package com.aproject.carsharing.exception;

public class PaymentTypeNotFoundException extends RuntimeException {
    public PaymentTypeNotFoundException(String message) {
        super(message);
    }
}
