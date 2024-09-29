package com.aproject.carsharing.car_sharing_service.dto.car;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarUpdateInventoryDto {
    @NotNull(message = "Inventory can't be null")
    @Positive(message = "Inventory should be positive")
    private int inventory;
}
