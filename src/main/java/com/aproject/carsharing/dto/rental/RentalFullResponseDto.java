package com.aproject.carsharing.dto.rental;

import com.aproject.carsharing.dto.car.CarFullResponseDto;
import com.aproject.carsharing.dto.user.UserResponseDto;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class RentalFullResponseDto {
    private Long id;
    private LocalDate rentalDate;
    private LocalDate returnDate;
    private LocalDate actualReturnDate;
    private CarFullResponseDto car;
    private UserResponseDto user;
}
