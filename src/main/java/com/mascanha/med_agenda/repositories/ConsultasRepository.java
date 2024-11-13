package com.mascanha.med_agenda.repositories;

import com.mascanha.med_agenda.entities.consultas.ConsultasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultasRepository extends JpaRepository<ConsultasEntity, Long> {
}
