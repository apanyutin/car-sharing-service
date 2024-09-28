package com.aproject.carsharing.car_sharing_service.service;

import com.aproject.carsharing.car_sharing_service.dto.car.CarFullResponseDto;
import com.aproject.carsharing.car_sharing_service.dto.car.CarRequestDto;
import com.aproject.carsharing.car_sharing_service.dto.car.CarShortResponseDto;
import com.aproject.carsharing.car_sharing_service.dto.car.CarUpdateInventoryDto;
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
