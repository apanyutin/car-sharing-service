package com.aproject.carsharing.car_sharing_service.service.impl;

import com.aproject.carsharing.car_sharing_service.dto.car.CarFullResponseDto;
import com.aproject.carsharing.car_sharing_service.dto.car.CarRequestDto;
import com.aproject.carsharing.car_sharing_service.dto.car.CarShortResponseDto;
import com.aproject.carsharing.car_sharing_service.dto.car.CarUpdateInventoryDto;
import com.aproject.carsharing.car_sharing_service.exception.EntityNotFoundException;
import com.aproject.carsharing.car_sharing_service.mapper.CarMapper;
import com.aproject.carsharing.car_sharing_service.model.Car;
import com.aproject.carsharing.car_sharing_service.repository.car.CarRepository;
import com.aproject.carsharing.car_sharing_service.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Override
    public CarFullResponseDto save(CarRequestDto requestDto) {
        Car car = carMapper.toModel(requestDto);
        return carMapper.toFullDto(carRepository.save(car));
    }

    @Override
    public CarFullResponseDto update(Long id, CarRequestDto requestDto) {
        Car car = carRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find car by id = " + id));
        carMapper.updateCarFromDto(car, requestDto);
        return carMapper.toFullDto(carRepository.save(car));
    }

    @Override
    public CarFullResponseDto updateInventory(Long id, CarUpdateInventoryDto updateInventoryDto) {
        Car car = carRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find car by id = " + id));
        carMapper.patchCarFromDto(car, updateInventoryDto);
        return carMapper.toFullDto(carRepository.save(car));
    }

    @Override
    public CarFullResponseDto getById(Long id) {
        Car car = carRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find car by id = " + id));
        return carMapper.toFullDto(car);
    }

    @Override
    public Page<CarShortResponseDto> getAll(Pageable pageable) {
        return carRepository.findAll(pageable)
                .map(carMapper::toShortDto);
    }

    @Override
    public void delete(Long id) {
        Car car = carRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find car by id = " + id));
        carRepository.delete(car);
    }
}
