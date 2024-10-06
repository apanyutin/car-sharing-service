package com.aproject.carsharing.repository.rental.spec;

import com.aproject.carsharing.model.Rental;
import com.aproject.carsharing.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class IsActiveSpecificationProvider implements SpecificationProvider<Rental> {
    public static final String IS_ACTIVE = "isActive";

    @Override
    public String getKey() {
        return IS_ACTIVE;
    }

    @Override
    public Specification<Rental> getSpecification(String param) {
        return (root, query, criteriaBuilder) -> {
            if (param.equals("true")) {
                return criteriaBuilder.isNull(root.get("actualReturnDate"));
            } else if (param.equals("false")) {
                return criteriaBuilder.isNotNull(root.get("actualReturnDate"));
            } else {
                throw new IllegalArgumentException(
                        "Param should contains true or false, but contains: " + param);
            }
        };
    }
}
