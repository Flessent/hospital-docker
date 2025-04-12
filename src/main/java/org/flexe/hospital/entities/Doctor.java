package org.flexe.hospital.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.springframework.data.relational.core.mapping.Table;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Doctor")
public class Doctor implements  Serializable {
     @Id
     @UuidGenerator
    private UUID id;
    private String name;
    //@Column(unique = true)
    private String email;
    private String speciality;
    //@Column(unique = true)
    private  String phone;
    private Date birthday;
    @Column(unique = true)
    private String username;
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    private  Address address;
    @OneToMany(mappedBy = "doctor",fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // when list a doctor, don't need to list also her/his meeting or Termin
    private Collection<Termin> doctorTermin;

    @ManyToMany(mappedBy = "doctors",fetch = FetchType.EAGER)
    private  Collection<Role> roles = new ArrayList<>(); // Instantiate the collectionTo avoid NullPointerException



}
