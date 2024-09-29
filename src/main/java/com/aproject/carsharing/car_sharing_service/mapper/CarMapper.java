package com.aproject.carsharing.car_sharing_service.mapper;

import com.aproject.carsharing.car_sharing_service.config.MapperConfig;
import com.aproject.carsharing.car_sharing_service.dto.car.CarFullResponseDto;
import com.aproject.carsharing.car_sharing_service.dto.car.CarRequestDto;
import com.aproject.carsharing.car_sharing_service.dto.car.CarShortResponseDto;
import com.aproject.carsharing.car_sharing_service.dto.car.CarUpdateInventoryDto;
import com.aproject.carsharing.car_sharing_service.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface CarMapper {
    Car toModel(CarRequestDto requestDto);

    CarShortResponseDto toShortDto(Car car);

    CarFullResponseDto toFullDto(Car car);

    void updateCarFromDto(@MappingTarget Car car, CarRequestDto requestDto);

    void patchCarFromDto(@MappingTarget Car car, CarUpdateInventoryDto updateInventoryDto);
}
