package com.aproject.carsharing.controller;

import com.aproject.carsharing.dto.user.UserLoginRequestDto;
import com.aproject.carsharing.dto.user.UserLoginResponseDto;
import com.aproject.carsharing.dto.user.UserRegistrationRequestDto;
import com.aproject.carsharing.dto.user.UserResponseDto;
import com.aproject.carsharing.exception.RegistrationException;
import com.aproject.carsharing.security.AuthService;
import com.aproject.carsharing.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication management",
        description = "Endpoints for registration and authorization")
public class AuthController {
    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "Register a user",
            description = "Register a new user if it don't exist in the DB")
    public UserResponseDto register(@RequestBody @Valid UserRegistrationRequestDto request)
            throws RegistrationException {
        return userService.register(request);
    }

    @PostMapping("/login")
    @Operation(summary = "Login a user",
            description = "Endpoint for logging in a user")
    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto requestDto) {
        return authService.authenticate(requestDto);
    }
}
