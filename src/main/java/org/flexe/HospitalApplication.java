package org.flexe;

import org.flexe.hospital.entities.*;
import org.flexe.repositories.*;
import org.flexe.services.interfaces.DoctorServices;
import org.flexe.services.interfaces.PatientServices;
import org.flexe.services.interfaces.RoleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalApplication {




	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);

	}
	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
/*
@Bean
	CommandLineRunner start(DoctorServices doctorServices,
							AddressRepository addressRepository,
							PatientRepository patientRepository,
							TerminRepository terminRepository,
							ConsultationRepository consultationRepository,
							PatientServices patientServices,
							RoleServices roleServices){

		return  args -> {
			Stream.of("Flexe","Merkel","Modestine")
					.forEach(name->{
						Doctor doctor= new Doctor();
						Address adresse = new Address();
						adresse.setPlz(38678);
						adresse.setStrasse("Am Rollbeg");
						adresse.setHausNummer("1");
						adresse.setOrt("CLZ");
						//addressRepository.save(adresse);

						doctor.setName(name);
						doctor.setEmail(name.toLowerCase()+"@gov.de");
						doctor.setUsername(name.toLowerCase()+"@gov.de");
						   doctor.setPassword(roleServices.encodePassword(name+"12345")); // Bcrypt Encoding from Spring security

						doctor.setSpeciality(Math.random()>0.5?"Zahnartz":"Kinderartz");
						doctor.setAddress(adresse);
						String num="0128867322";
						doctor.setPhone(Math.random()>0.5?"+237"+num:"+49"+num);
						doctorServices.saveDoctor(doctor);




					});




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
						patientRepository.save(patient);

					});

			patientServices.getAllPatients(0,2)
					.forEach(p ->{
						System.out.println(p.getName());
					});
			//Patient patient = patientRepository.findById(1L).orElseThrow(()->new Exception("Patient not found") );
			//Doctor doctor = doctorRepository.findById(1L).orElseThrow(()->new Exception("Doctor not found") );

			Patient patient = patientRepository.findAll().get(0); // get the first patient from the list
			Doctor doctor = doctorServices.getListDoctors().get(0); // get the first doctor from the list
			Termin termin = new Termin(); // create a meeting or einen Termin
			termin.setTerminHour(new Date());// set the time of the meeting
			termin.setStatus(StatusTermin.PENDING); // default status is PENDING
			termin.setDoctor(doctor); // assign a doctor to this Termin
			termin.setPatient(patient);
			terminRepository.save(termin);

           Consultation consultation= new Consultation();
		   consultation.setTermin(termin);
		   consultation.setConsultationDate(consultation.getTermin().getTerminHour());
		   consultation.setConsultationReport("Sick");
		   consultationRepository.save(consultation);

		   // Set the roles of the doctor

			Role role = new Role();
			role.setRoleName("ROLE_DOCTOR");
			role.setDescription("This role is only for doctors");
			roleServices.addNewRole(role);
			roleServices.addRoleToDoctor(doctor.getUsername(),role.getRoleName());

			// Test encrypted password (authentication)

			System.out.println(doctor.getPassword());

			try {
				Doctor authenticatedDoctor=doctorServices.authenticate(doctor.getUsername(),doctor.getPassword());
				System.out.println("Welcome Doctor  " +authenticatedDoctor.getName());

			}catch (Exception e){

				System.out.println(e.getMessage());

			}




		};





	}*/


}
