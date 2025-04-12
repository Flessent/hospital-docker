package org.flexe.hospital;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.flexe.hospital.entities.Address;
import org.flexe.hospital.entities.Patient;
import org.flexe.repositories.PatientRepository;
import org.flexe.restcontrollers.PatientRestController;
import org.flexe.services.interfaces.PatientServices;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;

import javax.inject.Inject;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebMvcTest(PatientRestController.class)

public class PatientControllerTest {
    List<Patient> patients;
    @Autowired
MockMvc mockMvc;
    @MockBean
    private PatientServices patientServices;

    @BeforeEach
    void setUp(){
        patients= new ArrayList<Patient>();

        Stream.of("Jena","Anna","Mueller","Linda")
                .forEach(name->{
                    Patient patient= new Patient();
                    Address adresse = new Address();
                    adresse.setPlz(66271);
                    adresse.setStrasse("Klosterstrasse");
                    adresse.setHausNummer("33");
                    adresse.setOrt("Kleinblittersdorf");
                    //addressRepository.save(adresse);

                    patient.setName(name);
                    patient.setEmail(name.toLowerCase()+"@gov.de");
                    patient.setSick(true);
                    patient.setBirthday(new Date());
                    String num="01299767322";
                    patient.setPhone(Math.random()>0.5?"+237"+num:"+49"+num);
                    patient.setAddress(adresse);

                    patients.add(patient);

                });
        patients.forEach(p ->{
            System.out.println("Name: "+ p.getName());
        });
}

    @Test
    void shouldFindAllPatients() throws Exception {
        when(patientServices.getListPatient()).thenReturn(patients);

        mockMvc.perform(get("/patient/all"))
                .andExpect(status().isOk());
    }

    @Test
    void getPatientByEmail() throws Exception {
        when(patientServices.findByEmail(patients.get(0).getEmail())).thenReturn(patients.get(0));

        mockMvc.perform(get("/patient/email/{email}",patients.get(0).getEmail()))
                .andExpect(status().isOk());
    }

    @Test
    void deletePatientByEmail() throws Exception {
        when(patientServices.deletePatient(patients.get(0).getEmail())).thenReturn(true);

        mockMvc.perform(delete("/patient/delete/{email}", patients.get(0).getEmail()))
                .andExpect(status().isOk());
    }

    @Test
    void addNewPatient() throws Exception {
    Patient newPatient=new Patient("New mocked Patient" , new Date(),"mocked@daemon.com","+4343443434",false);
            when(patientServices.savePatient(any(Patient.class))).thenReturn(newPatient);

        mockMvc.perform(post("/patient/create")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(newPatient))
                        )

                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/hospital/patient/" + newPatient.getCodePatient()));
    }

    @Test
    void updatePatientByEmail() throws Exception {
   Patient toUpdatedPatient=patients.get(1);
   toUpdatedPatient.setPhone("+666666666666");

        when(patientServices.updatePatient(any(Patient.class))).thenReturn(toUpdatedPatient);

        mockMvc.perform(put("/patient/update/{email}", patients.get(1).getEmail())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(new ObjectMapper().writeValueAsString(toUpdatedPatient))
                )
                .andExpect(jsonPath("$.phone").value(toUpdatedPatient.getPhone()))

                .andExpect(status().isAccepted());
    }







}
