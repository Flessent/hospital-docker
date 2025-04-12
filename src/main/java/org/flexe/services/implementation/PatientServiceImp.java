package org.flexe.services.implementation;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.flexe.hospital.entities.Patient;
import org.flexe.repositories.AddressRepository;
import org.flexe.repositories.PatientRepository;
import org.flexe.services.interfaces.PatientServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
@Transactional
@AllArgsConstructor // constructor injection
public class PatientServiceImp implements PatientServices {


    private PatientRepository patientRepository;
    private AddressRepository addressRepository;

    @Override
    public Patient findByName(String name) {
        return this.patientRepository.findByName(name) ;
    }

    @Override
    public Page<Patient> getAllPatients(int pageNum, int pageSize) {
        return this.patientRepository.getAllPatients(PageRequest.of(pageNum,pageSize));
    }

    @Override
    public Page<Patient> findPatientByBirthday(Date birthday, int pageNum, int pageSize) {
        return this.patientRepository.findByBirthday(birthday, PageRequest.of(pageNum, pageSize));
    }

    @Override
    public Patient savePatient(Patient patient) {
         /*if(patient.getAddress()!=null){
             addressRepository.save(patient.getAddress());
         }*/

        return this.patientRepository.save(patient);
    }

    @Override
    public List<Patient> getListPatient() {
        return this.patientRepository.findAll();
    }

    @Override
    public Patient updatePatient(Patient patient) {
           return  patientRepository.save(patient);
    }

    @Override
    public Optional<Patient> findById(UUID patientID) {
        return patientRepository.findById(patientID);
    }


    @Override
    public boolean deletePatient(String email) throws Exception {
       Optional<Patient> patient=patientRepository.findByEmail(email);
        if(patient.isPresent()){
            patientRepository.deleteByEmail(email);
             return true;
        } else throw  new Exception();
    }

    @Override
    public Patient findByCodePatient(UUID codePatient) {
        return this.patientRepository.findByCodePatient(codePatient).orElseThrow();
    }

    @Override
    public Patient findByEmail(String email) {
        return this.patientRepository.findByEmail(email).orElseThrow();
    }


}
