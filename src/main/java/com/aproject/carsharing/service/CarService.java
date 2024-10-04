package com.aproject.carsharing.service;

import com.aproject.carsharing.dto.car.CarFullResponseDto;
import com.aproject.carsharing.dto.car.CarRequestDto;
import com.aproject.carsharing.dto.car.CarShortResponseDto;
import com.aproject.carsharing.dto.car.CarUpdateInventoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarService {
    CarFullResponseDto save(CarRequestDto requestDto);

    CarFullResponseDto update(Long id, CarRequestDto requestDto);

    CarFullResponseDto updateInventory(Long id, CarUpdateInventoryDto updateInventoryDto);

    CarFullResponseDto getById(Long id);

    Page<CarShortResponseDto> getAll(Pageable pageable);

    void delete(Long id);
}
