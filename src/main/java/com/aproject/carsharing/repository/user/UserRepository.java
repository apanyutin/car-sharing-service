package com.aproject.carsharing.repository.user;

import com.aproject.carsharing.model.Role;
import com.aproject.carsharing.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = "roles")
    Optional<User> findByEmail(String email);

    List<User> findAllByRolesRoleName(Role.RoleName roleName);
}
