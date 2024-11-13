package com.mascanha.med_agenda.dto;

public record ResponseAllDto<T>(T dados, String mensagem, Object codRequisicao) {
}
