package com.aproject.carsharing.dto.payment;

import com.aproject.carsharing.model.Payment;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class PaymentStatusResponseDto {
    private String message;
    private String sessionId;
    private Payment.PaymentStatus status;
}
