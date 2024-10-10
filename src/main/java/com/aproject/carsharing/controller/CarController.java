package com.aproject.carsharing.controller;

import com.aproject.carsharing.dto.car.CarFullResponseDto;
import com.aproject.carsharing.dto.car.CarRequestDto;
import com.aproject.carsharing.dto.car.CarShortResponseDto;
import com.aproject.carsharing.dto.car.CarUpdateInventoryDto;
import com.aproject.carsharing.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/cars")
@Tag(name = "Car controller", description = "Endpoints for managing cars")
public class CarController {
    private final CarService carService;

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = "Create a new car", description = "Allowed only for manager")
    public CarFullResponseDto save(@RequestBody @Valid CarRequestDto requestDto) {
        return carService.save(requestDto);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PutMapping("/{id}")
    @Operation(summary = "Update a car by id", description = "Allowed only for manager")
    public CarFullResponseDto update(@PathVariable @Positive Long id,
                                     @RequestBody @Valid CarRequestDto requestDto) {
        return carService.update(id, requestDto);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @PatchMapping("/{id}")
    @Operation(summary = "Update a car inventory", description = "Allowed only for manager")
    public CarFullResponseDto updateInventory(@PathVariable @Positive Long id,
            @RequestBody @Valid CarUpdateInventoryDto updateInventoryDto) {
        return carService.updateInventory(id, updateInventoryDto);
    }

    @GetMapping
    @Operation(summary = "Find all cars", description = "Returns a page of available cars")
    public Page<CarShortResponseDto> findAll(@PageableDefault Pageable pageable) {
        return carService.getAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a car by id", description = "Returns a full info about car by id")
    public CarFullResponseDto findById(@PathVariable @Positive Long id) {
        return carService.getById(id);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a car by id", description = "Allowed only for manager")
    public void delete(@PathVariable @Positive Long id) {
        carService.delete(id);
    }
}
