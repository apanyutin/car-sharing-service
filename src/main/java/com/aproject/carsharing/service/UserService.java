package com.aproject.carsharing.service;

import com.aproject.carsharing.dto.user.UserRegistrationRequestDto;
import com.aproject.carsharing.dto.user.UserResponseDto;
import com.aproject.carsharing.dto.user.UserUpdateRequestDto;
import com.aproject.carsharing.dto.user.UserUpdateRoleRequestDto;
import com.aproject.carsharing.exception.RegistrationException;
import com.aproject.carsharing.model.User;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto registrationDto)
            throws RegistrationException;

    UserResponseDto getProfileInfo(User user);

    UserResponseDto updateProfileInfo(User user, UserUpdateRequestDto updateDto);

    UserResponseDto updateRole(Long id, UserUpdateRoleRequestDto updateRoleDto);
}
