package com.aproject.carsharing.dto.car;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class CarUpdateInventoryDto {
    @NotNull(message = "Inventory can't be null")
    @Positive(message = "Inventory should be positive")
    private int inventory;
}
