package com.aproject.carsharing.mapper;

import com.aproject.carsharing.config.MapperConfig;
import com.aproject.carsharing.dto.user.UserRegistrationRequestDto;
import com.aproject.carsharing.dto.user.UserResponseDto;
import com.aproject.carsharing.dto.user.UserUpdateRequestDto;
import com.aproject.carsharing.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toDto(User user);

    User toModel(UserRegistrationRequestDto registrationDto);

    void userUpdateFromDto(@MappingTarget User user, UserUpdateRequestDto updateDto);
}
