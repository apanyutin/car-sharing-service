package com.aproject.carsharing.car_sharing_service.repository.car;

import com.aproject.carsharing.car_sharing_service.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
