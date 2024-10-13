package com.aproject.carsharing.dto.payment;

import com.aproject.carsharing.model.Payment;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class PaymentFullResponseDto {
    private Long id;
    private String sessionUrl;
    private String sessionId;
    private BigDecimal amountToPay;
    private Long rentalId;
    private Payment.PaymentStatus status;
    private Payment.PaymentType type;
}
