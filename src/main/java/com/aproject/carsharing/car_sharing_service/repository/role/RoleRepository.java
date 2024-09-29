package com.aproject.carsharing.car_sharing_service.repository.role;

import com.aproject.carsharing.car_sharing_service.model.Role;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Set<Role> findByRoleName(Role.RoleName roleName);
}
