package org.flexe.repositories;

import org.flexe.hospital.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,String> {
    Role findRoleByRoleName(String roleName);

}
