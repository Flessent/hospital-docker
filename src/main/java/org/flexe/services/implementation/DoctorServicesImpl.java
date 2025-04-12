package org.flexe.services.implementation;

import lombok.AllArgsConstructor;
import org.flexe.hospital.entities.Doctor;
import org.flexe.hospital.entities.Patient;
import org.flexe.repositories.DoctorRepository;
import org.flexe.services.interfaces.DoctorServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class DoctorServicesImpl implements DoctorServices {

    private DoctorRepository doctorRepository;

    @Override
    public Doctor authenticate(String username, String password) {
        Doctor doctor=this.doctorRepository.findByUsername(username);
        if (doctor.getPassword().equals(password)){
            System.out.println("Authentication was succesfull !!!");
            return doctor;
        }
        else throw new RuntimeException("Bad credentials");

    }

    @Override
    public Doctor findByName(String name) {
        return this.doctorRepository.findByName(name);
    }

    @Override
    public Page<Doctor> getAllDoctors(int pageNum, int pageSize) {
        return this.doctorRepository.findAll(PageRequest.of(pageNum,pageSize));
    }

    @Override
    public Page<Doctor> findDoctorByBirthday(Date birthday, int pageNum, int pageSize) {
        return this.doctorRepository.findByBirthday(birthday,PageRequest.of(pageNum,pageSize));
    }

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        return this.doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> getListDoctors() {
        return this.doctorRepository.findAll();
    }
}
