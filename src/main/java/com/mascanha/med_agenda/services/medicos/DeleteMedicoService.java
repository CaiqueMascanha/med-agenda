package com.mascanha.med_agenda.services.medicos;

import com.mascanha.med_agenda.dto.ResponseAllDto;
import com.mascanha.med_agenda.dto.medicos.responses.ResponseMedicoDto;
import com.mascanha.med_agenda.entities.medicos.MedicosEntity;
import com.mascanha.med_agenda.exceptions.medicos.SemDadosMedicos;
import com.mascanha.med_agenda.repositories.MedicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteMedicoService {

    @Autowired
    private MedicosRepository medicosRepository;

    public ResponseAllDto execute(Long id) {
        Optional<MedicosEntity> medico = medicosRepository.findById(id);

        if (medico.isEmpty()) {
            throw new SemDadosMedicos("Médico não encontrado");
        }

        medicosRepository.deleteById(id);

        return new ResponseAllDto<>(null, "Médico deletado!", 200);
    }
}
