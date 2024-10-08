package com.aproject.carsharing.service.payment.impl;

import com.aproject.carsharing.exception.PaymentException;
import com.aproject.carsharing.model.Payment;
import com.aproject.carsharing.repository.payment.PaymentRepository;
import com.aproject.carsharing.service.payment.PaymentExpiredCheckerScheduledService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentExpiredCheckerScheduledServiceImpl
        implements PaymentExpiredCheckerScheduledService {
    private static final String EXPIRED_SESSION = "expired";
    private final PaymentRepository paymentRepository;

    @Scheduled(fixedRate = 60000)
    public void checkPendingPayments() {
        List<Payment> pendingPayments = paymentRepository
                .findByStatus(Payment.PaymentStatus.PENDING);
        if (!pendingPayments.isEmpty()) {
            for (Payment payment : pendingPayments) {
                updatePaymentStatus(payment);
            }
        }
    }

    public void updatePaymentStatus(Payment payment) {
        try {
            Session session = Session.retrieve(payment.getSessionId());
            String sessionStatus = session.getStatus();
            if (sessionStatus.equals(EXPIRED_SESSION)) {
                payment.setStatus(Payment.PaymentStatus.EXPIRED);
                paymentRepository.save(payment);
            }
        } catch (StripeException e) {
            throw new PaymentException("Error while checking payment for expiration");
        }
    }
}
