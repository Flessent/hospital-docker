package org.flexe.repositories;

import org.flexe.hospital.entities.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
    Doctor findByName(String name);

    Doctor findByUsername(String username);
    Page<Doctor> findByBirthday(Date birthday, Pageable pageable);
    /*@Query("Select d from Doctor d where  d.username=usnm, d.password=pwd ")
    @Transactional
    Doctor authentication(@Param("usnm") String username, @Param("pwd") String password);*/
}
