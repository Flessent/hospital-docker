package org.flexe.hospital.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "Address")
public class Address implements Serializable {
    @Id
    @UuidGenerator
    private UUID Id;
    @Column(length = 6, name = "plz")
    private int plz;

    private String strasse;
private String hausNummer;
private String ort;


@OneToOne(mappedBy = "address")
@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
private Patient patient;
@OneToOne(mappedBy = "address")
private  Doctor doctor;


}
