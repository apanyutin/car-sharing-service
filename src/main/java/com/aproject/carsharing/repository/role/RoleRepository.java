package com.aproject.carsharing.repository.role;

import com.aproject.carsharing.model.Role;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Set<Role> findByRoleName(Role.RoleName roleName);
}
