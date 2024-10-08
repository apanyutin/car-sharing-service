package com.aproject.carsharing.dto.payment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponseDto {
    private String sessionId;
    private String sessionUrl;
}
