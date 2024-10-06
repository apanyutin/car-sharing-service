package com.aproject.carsharing.repository.rental.spec;

import com.aproject.carsharing.model.Rental;
import com.aproject.carsharing.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class UserIdSpecificationProvider implements SpecificationProvider<Rental> {
    public static final String USER = "user";
    public static final String ID_FIELD = "id";

    @Override
    public String getKey() {
        return USER;
    }

    @Override
    public Specification<Rental> getSpecification(String param) {
        return (root, query, criteriaBuilder)
                -> criteriaBuilder.equal(root.get(USER).get(ID_FIELD), param);
    }
}
