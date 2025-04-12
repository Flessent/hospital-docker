package org.flexe.hospital.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.repository.query.Param;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "Termin")
@Data

@NoArgsConstructor
@AllArgsConstructor
public class Termin implements  Serializable  {


@Id
@UuidGenerator
private UUID id;


@Temporal(TemporalType.TIMESTAMP)
private Date terminHour;

@Enumerated(EnumType.STRING)
private StatusTermin status;

@ManyToOne
private Patient patient;

@ManyToOne
private Doctor doctor;


@OneToOne(mappedBy = "termin",fetch = FetchType.LAZY)
private  Consultation consultation;

}
