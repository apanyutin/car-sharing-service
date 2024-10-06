package com.aproject.carsharing.service;

import com.aproject.carsharing.dto.rental.RentalFullResponseDto;
import com.aproject.carsharing.dto.rental.RentalRequestDto;
import com.aproject.carsharing.dto.rental.RentalResponseDto;
import com.aproject.carsharing.dto.rental.RentalSearchParameters;
import com.aproject.carsharing.dto.rental.RentalSetActualReturnDateDto;
import com.aproject.carsharing.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RentalService {
    RentalFullResponseDto save(User user, RentalRequestDto requestDto);

    Page<RentalResponseDto> findAllByActiveStatus(RentalSearchParameters searchParameters,
                                                  Pageable pageable);

    RentalFullResponseDto findById(User user, Long id);

    RentalResponseDto returnRental(User user, Long id, RentalSetActualReturnDateDto returnDateDto);
}
