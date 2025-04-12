package org.flexe.repositories;

import org.flexe.hospital.entities.Termin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TerminRepository extends JpaRepository<Termin, UUID> {
}
