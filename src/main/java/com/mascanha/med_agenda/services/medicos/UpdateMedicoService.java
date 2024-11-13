package com.mascanha.med_agenda.services.medicos;

import com.mascanha.med_agenda.dto.ResponseAllDto;
import com.mascanha.med_agenda.dto.medicos.requests.RequestMedicoDto;
import com.mascanha.med_agenda.dto.medicos.responses.ResponseMedicoDto;
import com.mascanha.med_agenda.entities.medicos.MedicosEntity;
import com.mascanha.med_agenda.exceptions.medicos.SemDadosMedicos;
import com.mascanha.med_agenda.repositories.MedicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateMedicoService {

    @Autowired
    private MedicosRepository medicosRepository;

    public ResponseAllDto<ResponseMedicoDto> execute(Long id, RequestMedicoDto requestMedicoDto) {
        MedicosEntity medico = medicosRepository.findById(id)
                .orElseThrow(() -> new SemDadosMedicos("Médico não encontrado"));

        medico.setNome(requestMedicoDto.nome());
        medico.setEspecialidade(requestMedicoDto.especialidade());
        medico.setCrm(requestMedicoDto.crm());
        medico.setTelefone(requestMedicoDto.telefone());
        medico.setEmail(requestMedicoDto.email());

        MedicosEntity updateResponse = medicosRepository.save(medico);

        return new ResponseAllDto<>(updateResponse.toDto(), null, 200);
    }
}
