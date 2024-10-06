package com.aproject.carsharing.service.impl;

import com.aproject.carsharing.dto.rental.RentalFullResponseDto;
import com.aproject.carsharing.dto.rental.RentalRequestDto;
import com.aproject.carsharing.dto.rental.RentalResponseDto;
import com.aproject.carsharing.dto.rental.RentalSearchParameters;
import com.aproject.carsharing.dto.rental.RentalSetActualReturnDateDto;
import com.aproject.carsharing.exception.EntityNotFoundException;
import com.aproject.carsharing.exception.RentalException;
import com.aproject.carsharing.mapper.RentalMapper;
import com.aproject.carsharing.model.Car;
import com.aproject.carsharing.model.Rental;
import com.aproject.carsharing.model.User;
import com.aproject.carsharing.repository.SpecificationBuilder;
import com.aproject.carsharing.repository.car.CarRepository;
import com.aproject.carsharing.repository.rental.RentalRepository;
import com.aproject.carsharing.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {
    private final SpecificationBuilder<Rental> rentalSpecificationBuilder;
    private final RentalRepository rentalRepository;
    private final CarRepository carRepository;
    private final RentalMapper rentalMapper;

    @Transactional
    @Override
    public RentalFullResponseDto save(User user, RentalRequestDto requestDto) {
        Rental rental = rentalMapper.toModel(requestDto);
        rental.setUser(user);
        Car car = carRepository.findById(requestDto.getCarId()).orElseThrow(
                () -> new EntityNotFoundException(
                        "Can't find car by id = " + requestDto.getCarId()));
        if (car.getInventory() < 1) {
            throw new RentalException("No cars available anymore");
        }
        car.setInventory(car.getInventory() - 1);
        rental.setCar(car);
        return rentalMapper.toFullDto(rentalRepository.save(rental));
    }

    @Override
    public Page<RentalResponseDto> findAllByActiveStatus(
            RentalSearchParameters searchParameters,
            Pageable pageable
    ) {
        Specification<Rental> specification = rentalSpecificationBuilder.build(searchParameters);
        return rentalRepository.findAll(specification, pageable)
                .map(rentalMapper::toDto);
    }

    @Override
    public RentalFullResponseDto findById(User user, Long id) {
        Rental rental = rentalRepository.findByIdAndUser(id, user).orElseThrow(
                () -> new EntityNotFoundException("Can't find rental by id: " + id));
        return rentalMapper.toFullDto(rental);
    }

    @Transactional
    @Override
    public RentalResponseDto returnRental(User user,
                                          Long id,
                                          RentalSetActualReturnDateDto returnDateDto) {
        Rental rental = rentalRepository.findByIdAndUser(id, user).orElseThrow(
                () -> new EntityNotFoundException("Can't find rental by id: " + id));

        if (rental.getActualReturnDate() != null) {
            throw new RentalException(
                    String.format("Rental with id - %d already returned", id));
        }
        Long carId = rental.getCar().getId();
        Car car = carRepository.findById(carId).orElseThrow(
                () -> new EntityNotFoundException("Can't find car by id = " + carId));
        car.setInventory(car.getInventory() + 1);
        carRepository.save(car);
        rentalMapper.setActualReturnDateFromDto(rental, returnDateDto);
        return rentalMapper.toDto(rentalRepository.save(rental));
    }
}
