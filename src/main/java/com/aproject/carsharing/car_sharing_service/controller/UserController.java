package com.aproject.carsharing.car_sharing_service.controller;

import com.aproject.carsharing.car_sharing_service.dto.user.UserResponseDto;
import com.aproject.carsharing.car_sharing_service.dto.user.UserUpdateRequestDto;
import com.aproject.carsharing.car_sharing_service.dto.user.UserUpdateRoleRequestDto;
import com.aproject.carsharing.car_sharing_service.model.User;
import com.aproject.carsharing.car_sharing_service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "User profile controller", description = "Endpoints for profile operations")
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    @Operation(summary = "Get user info", description = "Get info about user from db")
    public UserResponseDto getInfo(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return userService.getProfileInfo(user);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PatchMapping("/{id}/role")
    @Operation(summary = "Update user role", description = "Allowed only for manager")
    public UserResponseDto updateRole(@PathVariable @Positive Long id,
                                      @RequestBody @Valid UserUpdateRoleRequestDto updateRoleDto) {
        return userService.updateRole(id, updateRoleDto);
    }

    @PutMapping("/me")
    @Operation(summary = "Update user info", description = "Updating exist user")
    public UserResponseDto updateInfo(Authentication authentication,
                                      @Valid @RequestBody UserUpdateRequestDto updateDto) {
        User user = (User) authentication.getPrincipal();
        return userService.updateProfileInfo(user, updateDto);
    }
}
