package com.aproject.carsharing.dto.payment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestDto {
    private Long rentalId;
    private String paymentType;
}
