package org.flexe.services.interfaces;

import org.flexe.hospital.entities.Doctor;
import org.flexe.hospital.entities.Role;



public interface RoleServices {

    Doctor addNewDoctor(Doctor doctor);
    Doctor findDoctorByUsername(String username);
    Role addNewRole(Role role);
    Role findRoleByRoleName(String roleName);
    void addRoleToDoctor(String username,String role);
String encodePassword(String password);


}
