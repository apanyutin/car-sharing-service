package com.aproject.carsharing.service.payment.impl;

import com.aproject.carsharing.dto.payment.PaymentFullResponseDto;
import com.aproject.carsharing.dto.payment.PaymentRequestDto;
import com.aproject.carsharing.dto.payment.PaymentResponseDto;
import com.aproject.carsharing.dto.payment.PaymentStatusResponseDto;
import com.aproject.carsharing.exception.EntityNotFoundException;
import com.aproject.carsharing.exception.PaymentException;
import com.aproject.carsharing.mapper.PaymentMapper;
import com.aproject.carsharing.model.Payment;
import com.aproject.carsharing.model.Rental;
import com.aproject.carsharing.model.User;
import com.aproject.carsharing.repository.payment.PaymentRepository;
import com.aproject.carsharing.repository.rental.RentalRepository;
import com.aproject.carsharing.service.payment.PaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StripePaymentService implements PaymentService {
    private static final String COMPLETE_SESSION_STATUS = "complete";
    private static final String DOMAIN = "http://localhost:8080";
    private static final String CANCELED_LINK = "/payments/success/{CHECKOUT_SESSION_ID}";
    private static final String SUCCESS_LINK = "/payments/cancel/{CHECKOUT_SESSION_ID}";
    private static final String SESSION_NAME = "Car Rental Payment";
    private static final int HOUR_IN_SECONDS = 3600;
    private final RentalRepository rentalRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final PaymentCalculateStrategy calculateStrategy;

    @Transactional
    @Override
    public PaymentResponseDto createPaymentSession(PaymentRequestDto paymentRequestDto, User user) {
        if (paymentRepository.findByRentalIdAndStatusIn(paymentRequestDto.getRentalId(),
                List.of(Payment.PaymentStatus.PAID, Payment.PaymentStatus.PENDING)).isPresent()) {
            throw new PaymentException("An error occurred while creating the payment");
        }

        Rental rental = rentalRepository.findById(paymentRequestDto.getRentalId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find rental by id: " + paymentRequestDto.getRentalId()));
        if (!rental.getUser().getId().equals(user.getId())) {
            throw new PaymentException("You don't have permission to this rental");
        }

        String paymentRequestType = paymentRequestDto.getPaymentType().toUpperCase();
        long amountToPay = calculateStrategy
                .getCalculateServiceByType(paymentRequestType)
                .calculateAmountToPay(rental).longValue();

        Session session = createStripeSession(amountToPay).orElseThrow(
                () -> new PaymentException("An error occurred while creating the payment"));
        Payment payment = new Payment()
                .setRental(rental)
                .setAmountToPay(BigDecimal.valueOf(amountToPay))
                .setSessionId(session.getId())
                .setSessionUrl(session.getUrl())
                .setStatus(Payment.PaymentStatus.PENDING)
                .setType(Payment.PaymentType.valueOf(paymentRequestType));
        paymentRepository.save(payment);
        return paymentMapper.toDto(payment);
    }

    @Override
    public Page<PaymentFullResponseDto> findAllByUserId(Long id, Pageable pageable) {
        return paymentRepository.findAllByRentalUserId(id, pageable)
                .map(paymentMapper::toFullDto);
    }

    @Transactional
    @Override
    public PaymentStatusResponseDto handleSuccess(String sessionId) {
        try {
            Payment payment = paymentRepository.findBySessionId(sessionId).orElseThrow(
                    () -> new EntityNotFoundException(
                            String.format("Can't find payment by sessionId = " + sessionId)));
            Session session = Session.retrieve(sessionId);
            if (session.getStatus().equals(COMPLETE_SESSION_STATUS)) {
                payment.setStatus(Payment.PaymentStatus.PAID);
                paymentRepository.save(payment);
                return paymentMapper.toStatusDto(payment)
                        .setMessage("Successful payment");
            }
            return paymentMapper.toStatusDto(payment)
                    .setMessage("Payment not completed");
        } catch (StripeException e) {
            throw new PaymentException("Stripe error with session: " + sessionId);
        }
    }

    @Transactional
    @Override
    public PaymentStatusResponseDto handleCancel(String sessionId) {
        Payment payment = paymentRepository.findBySessionId(sessionId).orElseThrow(
                () -> new EntityNotFoundException(
                        "Can't find payment by sessionId = " + sessionId));
        payment.setStatus(Payment.PaymentStatus.CANCELED);
        return paymentMapper.toStatusDto(paymentRepository.save(payment))
                .setMessage("Payment session canceled");
    }

    private Optional<Session> createStripeSession(long amountToPay) {
        try {
            SessionCreateParams params = buildSessionCreateParams(amountToPay);
            return Optional.of(Session.create(params));
        } catch (Exception ex) {
            throw new PaymentException("An error occurred while creating the payment");
        }
    }

    private SessionCreateParams buildSessionCreateParams(long amountToPay) {
        return SessionCreateParams.builder()
                .setExpiresAt(Instant.now().getEpochSecond() + HOUR_IN_SECONDS)
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(DOMAIN + SUCCESS_LINK)
                .setCancelUrl(DOMAIN + CANCELED_LINK)
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency("usd")
                                                .setUnitAmount(amountToPay * 100)
                                                .setProductData(
                                                        SessionCreateParams
                                                                .LineItem.PriceData
                                                                .ProductData.builder()
                                                                .setName(SESSION_NAME)
                                                                .build())
                                                .build())
                                .build())
                .build();
    }
}
