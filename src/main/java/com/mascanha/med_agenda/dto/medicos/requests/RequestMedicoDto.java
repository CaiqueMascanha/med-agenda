package com.mascanha.med_agenda.dto.medicos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RequestMedicoDto(
        String nome,
        String especialidade,
        String crm,
        String telefone,

        @Email(message = "Por favor, insira um e-mail válido!")
        @NotBlank(message = "Por favor, preencha o campo de e-mail")
        String email
) {
}
