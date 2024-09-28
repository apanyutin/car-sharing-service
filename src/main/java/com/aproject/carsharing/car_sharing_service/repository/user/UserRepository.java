package com.aproject.carsharing.car_sharing_service.repository.user;

import com.aproject.carsharing.car_sharing_service.model.Role;
import com.aproject.carsharing.car_sharing_service.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = "roles")
    Optional<User> findByEmail(String email);

    List<User> findAllByRolesName(Role.RoleName roleName);
}
