package com.mascanha.med_agenda.repositories;

import com.mascanha.med_agenda.entities.pacientes.PacientesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacientesRepository extends JpaRepository<PacientesEntity, Long> {
}
