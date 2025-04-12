package org.flexe.restcontrollers;


import org.flexe.hospital.entities.Patient;
import org.flexe.repositories.PatientRepository;
import org.flexe.services.interfaces.PatientServices;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/patient")
public class PatientRestController extends  RuntimeException {

    private PatientServices patientServices;

    PatientRestController(PatientServices patientServices){
        this.patientServices=patientServices;
    }


/*@GetMapping(path  = "/getAllPatient")
public Page<Patient> getAllPatient(){

    return this.patientServices.getAllPatients(1,2);//this.patientServices.getListPatient().size());

}*/

    @GetMapping(path = "/all")
    public ResponseEntity< List<Patient> > getAllPatient(){

        //this.patientServices.getListPatient().size());
        List<Patient> patients = this.patientServices.getListPatient();
        System.out.println("Get all Patient"+patients.get(0).getCodePatient());
        return new ResponseEntity<List<Patient>>(patients, HttpStatus.OK);
    }

    @GetMapping(path = "/email/{email}")
    public ResponseEntity<Patient> getPatientByEmail(@PathVariable("email") String email){
        return new ResponseEntity<Patient>(this.patientServices.findByEmail(email),HttpStatus.OK);
    }

    @GetMapping(path  = "/{codePatient}")
    public ResponseEntity<Patient> getPatientByCodePatient(@PathVariable(value = "codePatient") UUID codePatient){

        Patient patient = this.patientServices.findByCodePatient(codePatient);


        if (patient ==null) {
            System.out.println("get patient"+patient.getCodePatient());
            return   ResponseEntity.ok().body(patient); //or return new ResponseEntity<Patient>(patient,HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping(path = "/tobeupdated/{codePatient}",produces ={MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE} )
    public ResponseEntity<String> updatePatient(@PathVariable(value = "codePatient") UUID codePatient, @RequestBody Patient newPatientData) throws Exception{
        newPatientData.setCodePatient(codePatient);
Patient updatedPatient=patientServices.updatePatient(newPatientData);
return ResponseEntity.accepted().build();

    }

    @PutMapping(path = "/update/{email}",consumes ={MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE} )
    public ResponseEntity<Patient> updatePatientByEmail(@PathVariable(value = "email") String email, @RequestBody Patient newPatientData) throws Exception{
        newPatientData.setEmail(email);
        Patient updatedPatient=patientServices.updatePatient(newPatientData);
        return ResponseEntity.accepted().body(updatedPatient);

    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<Void> deletePatientByEmail(@PathVariable(value = "email") String email) throws Exception {
      patientServices.deletePatient(email);
return new ResponseEntity<>(HttpStatus.OK);

    }



    @PostMapping(path = "/create",consumes = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<String> saveNewPatient(@RequestBody Patient  patient){
        if(patient != null){

            patientServices.savePatient(patient);
            //URI urlNewAddedPatient = URI.create("/hospital/patient/" +patient.getEmail());
            URI urlNewAddedPatient = URI.create("/hospital/patient/" + patient.getCodePatient());
            System.out.println("Patient CODE *********:"+ patient.getCodePatient());
            System.out.println("Patient LINK ################:"+ urlNewAddedPatient);
            return   ResponseEntity.created(urlNewAddedPatient).build();
        }
        else System.out.println("Patient is null"); return  ResponseEntity.noContent().build();
    }



}
