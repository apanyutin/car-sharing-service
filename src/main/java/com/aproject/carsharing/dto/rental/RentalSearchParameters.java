package com.aproject.carsharing.dto.rental;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class RentalSearchParameters {
    private String userId;
    @NotNull(message = "IsActive can't be null")
    private String isActive;
}
