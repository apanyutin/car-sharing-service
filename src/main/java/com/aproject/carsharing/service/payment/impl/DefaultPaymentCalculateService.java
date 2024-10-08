package com.aproject.carsharing.service.payment.impl;

import java.time.temporal.ChronoUnit;

import com.aproject.carsharing.model.Payment;
import com.aproject.carsharing.model.Rental;
import com.aproject.carsharing.service.payment.PaymentCalculateService;
import java.math.BigDecimal;
import java.time.Duration;
import org.springframework.stereotype.Service;

@Service
public class DefaultPaymentCalculateService implements PaymentCalculateService {
    private static final String PAYMENT_TYPE = Payment.PaymentType.PAYMENT.name();

    @Override
    public String getPaymentType() {
        return PAYMENT_TYPE;
    }

    @Override
    public BigDecimal calculateAmountToPay(Rental rental) {
        long days = ChronoUnit.DAYS.between()
        long days = Duration.between(rental.getRentalDate(), rental.getReturnDate()).toDays();
        return rental.getCar().getDailyFee().multiply(BigDecimal.valueOf(days));
    }
}
