package org.flexe.hospital.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Roles")
@Data

public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String roleName;
    private String  description;
@ManyToMany
@JoinTable(name="DOCTOR_ROLES")
@ToString.Exclude//Exclude or remove this list of doctors when calling the roles
private Collection<Doctor> doctors= new ArrayList<>(); // Instantiate the collectionTo avoid NullPointerException;
}
