package com.aproject.carsharing.controller;

import com.aproject.carsharing.dto.rental.RentalFullResponseDto;
import com.aproject.carsharing.dto.rental.RentalRequestDto;
import com.aproject.carsharing.dto.rental.RentalResponseDto;
import com.aproject.carsharing.dto.rental.RentalSearchParameters;
import com.aproject.carsharing.dto.rental.RentalSetActualReturnDateDto;
import com.aproject.carsharing.model.Role;
import com.aproject.carsharing.model.User;
import com.aproject.carsharing.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/rentals")
@Tag(name = "Rental controller", description = "Endpoints for operations with rentals")
public class RentalController {
    private final RentalService rentalService;

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new rental",
            description = "Allows the customer to create a new rental")
    public RentalFullResponseDto createRental(Authentication authentication,
                                              @RequestBody @Valid RentalRequestDto requestDto) {
        User user = (User) authentication.getPrincipal();
        return rentalService.save(user, requestDto);
    }

    @GetMapping
    @Operation(summary = "Get page of rentals",
            description = "Allows the customer to find all his rentals filtered by isActive"
                    + "and allows the manager to find all rentals for any/all users")
    public Page<RentalResponseDto> findAllByActiveStatus(
            Authentication authentication,
            String userId, @NotBlank String isActive,
            @PageableDefault Pageable pageable
    ) {
        User user = (User) authentication.getPrincipal();
        RentalSearchParameters searchParameters = new RentalSearchParameters()
                .setUserId(user.getId().toString())
                .setIsActive(isActive);

        boolean isManager = user.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals(Role.RoleName.ROLE_MANAGER.name()));
        if (!isManager && Long.parseLong(userId) != user.getId()) {
            throw new AccessDeniedException("Not allowed to view rentals of another user");
        }
        if (isManager) {
            searchParameters.setUserId(userId != null ? userId : user.getId().toString());
        }
        return rentalService.findAllByActiveStatus(searchParameters, pageable);
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping("/{id}")
    @Operation(summary = "Get rental by id",
            description = "Allows the customer to find a rental by ID")
    public RentalFullResponseDto findRentalById(Authentication authentication,
                                                @PathVariable @Positive Long id) {
        User user = (User) authentication.getPrincipal();
        return rentalService.findById(user, id);
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PostMapping("/{id}/return")
    @Operation(summary = "Return rental",
            description = "Allows the customer to return a rental")
    public RentalResponseDto returnRental(
            Authentication authentication,
            @RequestBody @Valid RentalSetActualReturnDateDto returnDateDto,
            @PathVariable @Positive Long id
    ) {
        User user = (User) authentication.getPrincipal();
        return rentalService.returnRental(user, id, returnDateDto);
    }
}
