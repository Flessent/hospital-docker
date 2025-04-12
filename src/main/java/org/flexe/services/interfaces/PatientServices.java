package org.flexe.services.interfaces;

import org.flexe.hospital.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PatientServices{
    Patient findByName(String name);

    Page<Patient> getAllPatients(int pageNum, int pageSize);
    Page<Patient> findPatientByBirthday(Date birthday,int pageNum, int pageSize);
    Patient savePatient(Patient patient);
    List<Patient> getListPatient();
   Patient updatePatient(Patient patient) ;

    Optional<Patient> findById(UUID patientID);

    boolean deletePatient(String email) throws Exception;
    Patient findByCodePatient(UUID codePatient);
    Patient findByEmail(String email);

}
