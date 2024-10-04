package com.aproject.carsharing.mapper;

import com.aproject.carsharing.config.MapperConfig;
import com.aproject.carsharing.dto.car.CarFullResponseDto;
import com.aproject.carsharing.dto.car.CarRequestDto;
import com.aproject.carsharing.dto.car.CarShortResponseDto;
import com.aproject.carsharing.dto.car.CarUpdateInventoryDto;
import com.aproject.carsharing.model.Car;
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
