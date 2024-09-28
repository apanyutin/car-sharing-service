package com.aproject.carsharing.car_sharing_service.dto.car;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarShortResponseDto {
    private Long id;
    private String model;
    private String carType;
}
