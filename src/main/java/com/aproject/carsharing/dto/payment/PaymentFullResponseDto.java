package com.aproject.carsharing.dto.payment;

import com.aproject.carsharing.model.Payment;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentFullResponseDto {
    private Long id;
    private String sessionUrl;
    private String sessionId;
    private BigDecimal amountToPay;
    private Long rentalId;
    private Payment.PaymentStatus status;
    private Payment.PaymentType type;
}
