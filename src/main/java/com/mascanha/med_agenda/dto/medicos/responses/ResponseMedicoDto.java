package com.mascanha.med_agenda.dto.medicos.responses;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ResponseMedicoDto(
        String nome,
        String especialidade,
        String crm,
        String telefone,
        String email,

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime dataDeCadastro
) {
}
