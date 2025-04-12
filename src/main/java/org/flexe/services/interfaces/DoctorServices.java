package org.flexe.services.interfaces;

import org.flexe.hospital.entities.Doctor;
import org.flexe.hospital.entities.Patient;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

public interface DoctorServices {

    Doctor authenticate(String username, String password);

    Doctor findByName(String name);

    Page<Doctor> getAllDoctors(int pageNum, int pageSize);
    Page<Doctor> findDoctorByBirthday(Date birthday, int pageNum, int pageSize);
    Doctor saveDoctor(Doctor doctor);
    List<Doctor> getListDoctors();

}
