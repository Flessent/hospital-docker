package org.flexe.hospital.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.annotation.Validated;
//import org.springframework.data.relational.core.mapping.Table;


@Entity
@Data
@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "Patient")
public class Patient implements  Serializable  {
@Id
@UuidGenerator
@Column(name = "idPatient")
@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
private UUID codePatient;
private String name;
@Temporal(TemporalType.DATE)
private  Date birthday;
private  boolean isSick ;
@Column(unique = true)

private  String email;
//@Column(unique = true)
private  String phone;

@OneToOne(cascade = CascadeType.ALL) /*
 the CASCADE means that, each time you want to save a patient, save also directly its corresponding address
 i.e we don't need to do addressRepository.save(address) then patientRepository.save(patient).
 We just need to set the address and call once patientRepository.save(patient). Hibernate will take or extract the provided address and save in the address table in DB

 */
private  Address address;
@OneToMany(mappedBy ="patient",fetch = FetchType.LAZY)
@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)// when list a patient , don't need to list also her/his meeting or Termin
private Collection<Termin> patientTermin;

public Patient(){}
public  Patient(String name,Date birthday,String email,String phone,boolean isSick){
    this.name=name;
    this.birthday=birthday;
    this.email=email;
    this.phone=phone;
    this.isSick=isSick;
}


}
