package com.aproject.carsharing.mapper;

import com.aproject.carsharing.config.MapperConfig;
import com.aproject.carsharing.dto.payment.PaymentFullResponseDto;
import com.aproject.carsharing.dto.payment.PaymentResponseDto;
import com.aproject.carsharing.dto.payment.PaymentStatusResponseDto;
import com.aproject.carsharing.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface PaymentMapper {
    PaymentResponseDto toDto(Payment payment);

    PaymentStatusResponseDto toStatusDto(Payment payment);

    @Mapping(target = "rentalId", source = "rental.id")
    PaymentFullResponseDto toFullDto(Payment payment);
}
