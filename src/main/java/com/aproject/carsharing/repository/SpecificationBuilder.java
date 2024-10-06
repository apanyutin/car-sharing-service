package com.aproject.carsharing.repository;

import com.aproject.carsharing.dto.rental.RentalSearchParameters;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T> {
    Specification<T> build(RentalSearchParameters rentalSearchParameters);
}
