package com.aproject.carsharing.service.impl;

import com.aproject.carsharing.dto.user.UserRegistrationRequestDto;
import com.aproject.carsharing.dto.user.UserResponseDto;
import com.aproject.carsharing.dto.user.UserUpdateRequestDto;
import com.aproject.carsharing.dto.user.UserUpdateRoleRequestDto;
import com.aproject.carsharing.dto.user.UserUpdateTgChatIdRequestDto;
import com.aproject.carsharing.exception.EntityNotFoundException;
import com.aproject.carsharing.exception.ProfileException;
import com.aproject.carsharing.exception.RegistrationException;
import com.aproject.carsharing.mapper.UserMapper;
import com.aproject.carsharing.model.Role;
import com.aproject.carsharing.model.User;
import com.aproject.carsharing.repository.role.RoleRepository;
import com.aproject.carsharing.repository.user.UserRepository;
import com.aproject.carsharing.service.UserService;
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

    @Override
    public UserResponseDto updateTgChatId(Long id, UserUpdateTgChatIdRequestDto updateChatIdDto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find user by id = " + id));
        user.setTgChatId(updateChatIdDto.getTgChatId());
        return userMapper.toDto(userRepository.save(user));
    }
}
