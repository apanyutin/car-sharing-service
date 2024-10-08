package com.aproject.carsharing.service.payment;

import com.aproject.carsharing.dto.payment.PaymentFullResponseDto;
import com.aproject.carsharing.dto.payment.PaymentRequestDto;
import com.aproject.carsharing.dto.payment.PaymentResponseDto;
import com.aproject.carsharing.dto.payment.PaymentStatusResponseDto;
import com.aproject.carsharing.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaymentService {
    PaymentResponseDto createPaymentSession(PaymentRequestDto paymentRequestDto, User user);

    Page<PaymentFullResponseDto> findAllByUserId(Long id, Pageable pageable);

    PaymentStatusResponseDto handleSuccess(String sessionId);

    PaymentStatusResponseDto handleCancel(String sessionId);
}
