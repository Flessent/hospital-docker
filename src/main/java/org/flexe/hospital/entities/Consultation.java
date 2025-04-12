package org.flexe.hospital.entities;
import jakarta.persistence.OneToOne;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import  jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.springframework.data.relational.core.mapping.Table;
import  jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Consultation")
public class Consultation implements  Serializable {
@Id
@UuidGenerator
private UUID idConsultation;
//@Column(name = "consultationDate")
private Date consultationDate;
private String consultationReport;

@OneToOne
private  Termin termin;


}
