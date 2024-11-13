package com.mascanha.med_agenda.repositories;

import com.mascanha.med_agenda.entities.medicos.MedicosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicosRepository extends JpaRepository<MedicosEntity, Long> {
    Optional<MedicosEntity> findByCrm(String cmg);
}
