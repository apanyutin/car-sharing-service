package com.aproject.carsharing.dto.car;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class CarShortResponseDto {
    private Long id;
    private String model;
    private String carType;
}
