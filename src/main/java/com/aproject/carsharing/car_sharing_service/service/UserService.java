package com.aproject.carsharing.car_sharing_service.service;

import com.aproject.carsharing.car_sharing_service.dto.user.UserRegistrationRequestDto;
import com.aproject.carsharing.car_sharing_service.dto.user.UserResponseDto;
import com.aproject.carsharing.car_sharing_service.dto.user.UserUpdateRequestDto;
import com.aproject.carsharing.car_sharing_service.dto.user.UserUpdateRoleRequestDto;
import com.aproject.carsharing.car_sharing_service.exception.RegistrationException;
import com.aproject.carsharing.car_sharing_service.model.User;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto registrationDto)
            throws RegistrationException;

    UserResponseDto getProfileInfo(User user);

    UserResponseDto updateProfileInfo(User user, UserUpdateRequestDto updateDto);

    UserResponseDto updateRole(Long id, UserUpdateRoleRequestDto updateRoleDto);
}
