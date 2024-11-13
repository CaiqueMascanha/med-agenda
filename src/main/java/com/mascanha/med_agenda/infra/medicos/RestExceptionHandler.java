package com.mascanha.med_agenda.infra.medicos;

import com.mascanha.med_agenda.dto.exceptions.ExceptionsDto;
import com.mascanha.med_agenda.exceptions.medicos.MedicosExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MedicosExceptions.class)
    private ResponseEntity<ExceptionsDto> medicoJaCadastrado(MedicosExceptions medicosExceptions) {

        ExceptionsDto erroDto = new ExceptionsDto(medicosExceptions.getMessage(), 400);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroDto);
    }
}
