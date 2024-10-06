package com.aproject.carsharing.mapper;

import com.aproject.carsharing.config.MapperConfig;
import com.aproject.carsharing.dto.rental.RentalFullResponseDto;
import com.aproject.carsharing.dto.rental.RentalRequestDto;
import com.aproject.carsharing.dto.rental.RentalResponseDto;
import com.aproject.carsharing.dto.rental.RentalSetActualReturnDateDto;
import com.aproject.carsharing.model.Rental;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class, uses = {CarMapper.class, UserMapper.class})
public interface RentalMapper {
    Rental toModel(RentalRequestDto requestDto);

    @Mapping(target = "carId", source = "car.id")
    @Mapping(target = "userId", source = "user.id")
    RentalResponseDto toDto(Rental rental);

    RentalFullResponseDto toFullDto(Rental rental);

    void setActualReturnDateFromDto(@MappingTarget Rental rental,
                                    RentalSetActualReturnDateDto returnDateDto);
}
