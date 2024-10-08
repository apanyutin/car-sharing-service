package com.aproject.carsharing.service.payment;

import com.aproject.carsharing.model.Rental;
import java.math.BigDecimal;

public interface PaymentCalculateService {
    String getPaymentType();

    BigDecimal calculateAmountToPay(Rental rental);
}
