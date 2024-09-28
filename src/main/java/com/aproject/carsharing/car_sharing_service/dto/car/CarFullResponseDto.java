package com.aproject.carsharing.car_sharing_service.dto.car;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarFullResponseDto {
    private Long id;
    private String model;
    private String brand;
    private String carType;
    private int inventory;
    private BigDecimal dailyFee;
}
