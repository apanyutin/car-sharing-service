package com.aproject.carsharing.repository.rental;

import com.aproject.carsharing.dto.rental.RentalSearchParameters;
import com.aproject.carsharing.model.Rental;
import com.aproject.carsharing.repository.SpecificationBuilder;
import com.aproject.carsharing.repository.SpecificationProviderManager;
import com.aproject.carsharing.repository.rental.spec.IsActiveSpecificationProvider;
import com.aproject.carsharing.repository.rental.spec.UserIdSpecificationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RentalSpecificationBuilder implements SpecificationBuilder<Rental> {
    private static final String IS_ACTIVE_SPECIFICATION = "actualReturnDate";
    private final SpecificationProviderManager<Rental> rentalSpecificationProviderManager;

    @Override
    public Specification<Rental> build(RentalSearchParameters searchParameters) {
        Specification<Rental> specification = Specification.where(null);
        if (searchParameters.getUserId() != null) {
            specification = specification.and(rentalSpecificationProviderManager
                    .getSpecificationProvider(UserIdSpecificationProvider.USER)
                    .getSpecification(searchParameters.getUserId()));
        }
        if (searchParameters.getIsActive() != null) {
            specification = specification.and(rentalSpecificationProviderManager
                    .getSpecificationProvider(IsActiveSpecificationProvider.IS_ACTIVE)
                    .getSpecification(searchParameters.getIsActive()));
        }
        return specification;
    }
}
