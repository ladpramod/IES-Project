package com.ies.module.admin.api.repository;

import com.ies.module.admin.api.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    public Role findByRoleName(String roleName);
}
