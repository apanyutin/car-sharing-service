package com.aproject.carsharing.service.payment.impl;

import com.aproject.carsharing.exception.PaymentTypeNotFoundException;
import com.aproject.carsharing.service.payment.PaymentCalculateService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentCalculateStrategy {
    private final List<PaymentCalculateService> paymentCalculateHandlers;

    public PaymentCalculateService getCalculateServiceByType(String paymentType) {
        for (PaymentCalculateService service : paymentCalculateHandlers) {
            if (service.getPaymentType().equals(paymentType)) {
                return service;
            }
        }
        throw new PaymentTypeNotFoundException(
                "No payment service found for payment type: " + paymentType);
    }
}
