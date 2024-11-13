package com.mascanha.med_agenda.services.medicos;

import com.mascanha.med_agenda.dto.ResponseAllDto;
import com.mascanha.med_agenda.dto.medicos.responses.ResponseMedicoDto;
import com.mascanha.med_agenda.entities.medicos.MedicosEntity;
import com.mascanha.med_agenda.exceptions.medicos.SemDadosMedicos;
import com.mascanha.med_agenda.repositories.MedicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllMedicosService {

    @Autowired
    private MedicosRepository medicosRepository;

    public ResponseAllDto<List<ResponseMedicoDto>> execute() {
        List<MedicosEntity> result = medicosRepository.findAll();

        if(result.isEmpty()) {
            throw new SemDadosMedicos("Sem dados médicos");
        }

        List<ResponseMedicoDto> response = result.stream()
                .map(medico -> medico.toDto())
                .collect(Collectors.toList());

        return new ResponseAllDto<>(response, null, 200);
    }
}
