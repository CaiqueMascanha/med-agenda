package com.mascanha.med_agenda.services.medicos;

import com.mascanha.med_agenda.dto.medicos.requests.RequestMedicoDto;
import com.mascanha.med_agenda.dto.medicos.responses.ResponseMedicoDto;
import com.mascanha.med_agenda.entities.medicos.MedicosEntity;
import com.mascanha.med_agenda.exceptions.medicos.MedicosExceptions;
import com.mascanha.med_agenda.repositories.MedicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatedMedicosService {

    @Autowired
    private MedicosRepository medicosRepository;

    public ResponseMedicoDto execute(RequestMedicoDto requestMedicoDto) {
        MedicosEntity medicosEntity = MedicosEntity.fromDto(requestMedicoDto);

        medicosRepository.findByCrm(medicosEntity.getCrm()).ifPresent(
                (medico) -> {
                    throw new MedicosExceptions("Médico já cadastrado!");
                }
        );

        MedicosEntity saveMedico = medicosRepository.save(medicosEntity);

        return saveMedico.toDto();
    }

}
