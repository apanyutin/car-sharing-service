package com.aproject.carsharing.repository.rental;

import com.aproject.carsharing.model.Rental;
import com.aproject.carsharing.model.User;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    Page<Rental> findAll(Specification<Rental> spec, Pageable pageable);

    Optional<Rental> findByIdAndUser(Long id, User user);

    @Query("SELECT r FROM Rental r JOIN FETCH r.car c JOIN FETCH r.user u "
            + "WHERE r.actualReturnDate IS NULL "
            + "AND r.returnDate <= :currentDate "
            + "AND r.isDeleted = false")
    List<Rental> findOverdueRentals(LocalDate currentDate);
}
