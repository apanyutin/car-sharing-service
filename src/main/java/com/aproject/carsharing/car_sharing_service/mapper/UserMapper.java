package com.aproject.carsharing.car_sharing_service.mapper;

import com.aproject.carsharing.car_sharing_service.config.MapperConfig;
import com.aproject.carsharing.car_sharing_service.dto.user.UserRegistrationRequestDto;
import com.aproject.carsharing.car_sharing_service.dto.user.UserResponseDto;
import com.aproject.carsharing.car_sharing_service.dto.user.UserUpdateRequestDto;
import com.aproject.carsharing.car_sharing_service.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toDto(User user);

    User toModel(UserRegistrationRequestDto registrationDto);

    void userUpdateFromDto(@MappingTarget User user, UserUpdateRequestDto updateDto);
}
