package com.aproject.carsharing.service.payment.impl;

import static java.time.temporal.ChronoUnit.DAYS;

import com.aproject.carsharing.model.Payment;
import com.aproject.carsharing.model.Rental;
import com.aproject.carsharing.service.payment.PaymentCalculateService;
import java.math.BigDecimal;
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
        long days = DAYS.between(rental.getRentalDate(), rental.getReturnDate());
        return rental.getCar().getDailyFee().multiply(BigDecimal.valueOf(days));
    }
}
