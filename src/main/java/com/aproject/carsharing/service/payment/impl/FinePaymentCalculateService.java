package com.aproject.carsharing.service.payment.impl;

import static java.time.temporal.ChronoUnit.DAYS;

import com.aproject.carsharing.model.Payment;
import com.aproject.carsharing.model.Rental;
import com.aproject.carsharing.service.payment.PaymentCalculateService;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class FinePaymentCalculateService implements PaymentCalculateService {
    private static final String PAYMENT_TYPE = Payment.PaymentType.FINE.name();
    private static final float FINE_MULTIPLIER = 1.1f;

    @Override
    public String getPaymentType() {
        return PAYMENT_TYPE;
    }

    @Override
    public BigDecimal calculateAmountToPay(Rental rental) {
        long days = DAYS.between(rental.getReturnDate(), rental.getActualReturnDate());
        return rental.getCar().getDailyFee()
                .multiply(BigDecimal.valueOf(days))
                .multiply(BigDecimal.valueOf(FINE_MULTIPLIER));
    }
}
