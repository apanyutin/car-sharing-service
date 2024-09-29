package com.aproject.carsharing.car_sharing_service.service.impl;

import com.aproject.carsharing.car_sharing_service.dto.user.UserRegistrationRequestDto;
import com.aproject.carsharing.car_sharing_service.dto.user.UserResponseDto;
import com.aproject.carsharing.car_sharing_service.dto.user.UserUpdateRequestDto;
import com.aproject.carsharing.car_sharing_service.dto.user.UserUpdateRoleRequestDto;
import com.aproject.carsharing.car_sharing_service.exception.EntityNotFoundException;
import com.aproject.carsharing.car_sharing_service.exception.ProfileException;
import com.aproject.carsharing.car_sharing_service.exception.RegistrationException;
import com.aproject.carsharing.car_sharing_service.mapper.UserMapper;
import com.aproject.carsharing.car_sharing_service.model.Role;
import com.aproject.carsharing.car_sharing_service.model.User;
import com.aproject.carsharing.car_sharing_service.repository.role.RoleRepository;
import com.aproject.carsharing.car_sharing_service.repository.user.UserRepository;
import com.aproject.carsharing.car_sharing_service.service.UserService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto registrationDto)
            throws RegistrationException {
        if (userRepository.findByEmail(registrationDto.getEmail()).isPresent()) {
            throw new RegistrationException(registrationDto.getEmail() + " is already registered");
        }
        User user = userMapper.toModel(registrationDto)
                .setPassword(passwordEncoder.encode(registrationDto.getPassword()))
                .setRoles(roleRepository.findByRoleName(Role.RoleName.ROLE_CUSTOMER));
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserResponseDto getProfileInfo(User user) {
        return userMapper.toDto(user);
    }

    @Override
    public UserResponseDto updateProfileInfo(User user, UserUpdateRequestDto updateDto) {
        if (!updateDto.getEmail().equals(user.getEmail())
                || userRepository.findByEmail(updateDto.getEmail()).isEmpty()) {
            throw new ProfileException(updateDto.getEmail() + " don't exist");
        }
        userMapper.userUpdateFromDto(user, updateDto);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserResponseDto updateRole(Long id, UserUpdateRoleRequestDto updateRoleDto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find user by id = " + id));
        Set<Role> roles = roleRepository.findByRoleName(
                Role.RoleName.valueOf(updateRoleDto.getRoleName())
        );
        user.setRoles(roles);
        return userMapper.toDto(userRepository.save(user));
    }
}

