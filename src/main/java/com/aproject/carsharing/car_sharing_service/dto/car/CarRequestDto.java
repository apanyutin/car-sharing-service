package com.aproject.carsharing.car_sharing_service.dto.car;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class CarRequestDto {
    @NotBlank(message = "The model field can't be blank")
    @Length(max = 255, message = "The model field can't be longer than 255")
    private String model;

    @NotBlank(message = "The brand field can't be blank")
    @Length(max = 255, message = "The brand field can't be longer than 255")
    private String brand;

    @NotBlank(message = "The car type field can't be blank")
    @Length(max = 50, message = "The car type field can't be longer than 50")
    private String carType;

    @NotNull(message = "The inventory field can't be null")
    @Positive(message = "The inventory field should be positive")
    private int inventory;

    @NotNull(message = "The daily fee field can't be null")
    @Positive(message = "The daily fee field should be positive")
    private BigDecimal dailyFee;
}
