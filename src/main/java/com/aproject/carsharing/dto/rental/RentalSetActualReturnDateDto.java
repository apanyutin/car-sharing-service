package com.aproject.carsharing.dto.rental;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class RentalSetActualReturnDateDto {
    @NotNull(message = "Actual return date can't be null")
    private LocalDate actualReturnDate;
}
