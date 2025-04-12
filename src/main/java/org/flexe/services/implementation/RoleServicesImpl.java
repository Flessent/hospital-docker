package org.flexe.services.implementation;

import org.flexe.hospital.entities.Doctor;
import org.flexe.hospital.entities.Role;
import org.flexe.repositories.DoctorRepository;
import org.flexe.repositories.RoleRepository;
import org.flexe.services.interfaces.RoleServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Service
@Transactional
public class RoleServicesImpl implements RoleServices {

    private DoctorRepository doctorRepository;
    private RoleRepository roleRepository;

    public RoleServicesImpl(DoctorRepository doctorRepository, RoleRepository roleRepository) {
        this.doctorRepository = doctorRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Doctor addNewDoctor(Doctor doctor) {
        return this.doctorRepository.save(doctor);
    }

    @Override
    public Doctor findDoctorByUsername(String username) {
        return this.doctorRepository.findByUsername(username);
    }

    @Override
    public Role addNewRole(Role role) {
        return this.roleRepository.save(role);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return this.roleRepository.findRoleByRoleName(roleName);
    }

    @Override
    public void addRoleToDoctor(String username, String roleName) {
        Doctor doctor=this.doctorRepository.findByUsername(username);
        Role role =this.roleRepository.findRoleByRoleName(roleName);
        if(doctor.getRoles()!=null){
            doctor.getRoles().add(role);
            role.getDoctors().add(doctor);
        }


    }




        @Override
    public String encodePassword(String password) {
            BCryptPasswordEncoder bCryptPasswordEncoder =new BCryptPasswordEncoder();
            return  bCryptPasswordEncoder.encode(password);
    }
}
