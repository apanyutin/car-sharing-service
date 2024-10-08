package com.aproject.carsharing.controller;

import com.aproject.carsharing.dto.payment.PaymentFullResponseDto;
import com.aproject.carsharing.dto.payment.PaymentRequestDto;
import com.aproject.carsharing.dto.payment.PaymentResponseDto;
import com.aproject.carsharing.dto.payment.PaymentStatusResponseDto;
import com.aproject.carsharing.model.Role;
import com.aproject.carsharing.model.User;
import com.aproject.carsharing.service.payment.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
@Tag(name = "Payment controller", description = "Endpoints for payments operations")
public class PaymentController {
    private final PaymentService paymentService;

    @PreAuthorize("hasRole('ROLE_CUSTOMER') or hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Create a new payment",
            description = "Allows a user's to create a new payment")
    public PaymentResponseDto createPayment(Authentication authentication,
                                            @RequestBody @Valid PaymentRequestDto requestDto) {
        User user = (User) authentication.getPrincipal();
        return paymentService.createPaymentSession(requestDto, user);
    }

    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER', 'ROLE_MANAGER')")
    @GetMapping
    @Operation(summary = "Find all payments",
            description = "Returns a page of payments by user id")
    public Page<PaymentFullResponseDto> findAll(Authentication authentication,
                                                @Positive Long userId,
                                                @PageableDefault Pageable pageable) {
        User user = (User) authentication.getPrincipal();
        if (user.getAuthorities().stream()
                .anyMatch(role ->
                        role.getAuthority().equals(Role.RoleName.ROLE_MANAGER.name()))) {
            return paymentService.findAllByUserId(userId, pageable);
        }
        return paymentService.findAllByUserId(user.getId(), pageable);
    }

    @GetMapping("/success/{sessionId}")
    @Operation(summary = "Handle success payment",
            description = "Returns a message from success payment")
    public PaymentStatusResponseDto handleSuccess(@PathVariable @NotBlank String sessionId) {
        return paymentService.handleSuccess(sessionId);
    }

    @GetMapping("/cancel/{sessionId}")
    @Operation(summary = "Handle canceled payment",
            description = "Returns a message from canceled payment")
    public PaymentStatusResponseDto handleCancel(@PathVariable @NotBlank String sessionId) {
        return paymentService.handleCancel(sessionId);
    }
}
