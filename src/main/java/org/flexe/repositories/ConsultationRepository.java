package org.flexe.repositories;

import org.flexe.hospital.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ConsultationRepository  extends JpaRepository<Consultation, UUID> {
}
