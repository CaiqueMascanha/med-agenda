package com.mascanha.med_agenda.infra.medicos;

import com.mascanha.med_agenda.dto.ResponseAllDto;
import com.mascanha.med_agenda.dto.exceptions.ExceptionsDto;
import com.mascanha.med_agenda.exceptions.medicos.SemDadosMedicos;
import com.mascanha.med_agenda.exceptions.medicos.MedicoJaCadastrado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler  {

    @ExceptionHandler(MedicoJaCadastrado.class)
    private ResponseEntity<ResponseAllDto> medicoJaCadastrado(MedicoJaCadastrado medicoJaCadastrado) {

        ResponseAllDto erroDto = new ResponseAllDto<>(null, medicoJaCadastrado.getMessage(), 400);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroDto);
    }

    @ExceptionHandler(SemDadosMedicos.class)
    private ResponseEntity<ResponseAllDto> semDadosMedicos(SemDadosMedicos semDadosMedicos) {
        ResponseAllDto erroDto = new ResponseAllDto<>(new ArrayList<>(), semDadosMedicos.getMessage(), 200);

        return ResponseEntity.status(HttpStatus.OK).body(erroDto);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    private ResponseEntity<ResponseAllDto> dadosIncorretos(HttpMessageNotReadableException e) {
        ResponseAllDto erroDto = new ResponseAllDto(new ArrayList<>(), "Dados fornecidos estão malformados.", 400);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroDto);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseAllDto<List<ExceptionsDto>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        // Obtém a lista de erros de validação do BindingResult
        BindingResult result = ex.getBindingResult();
        List<ExceptionsDto> exceptionsDto = new ArrayList<>();

        // Cria uma lista de mensagens de erro a partir dos erros de validação
        result.getFieldErrors()
                .stream()
                .map(fieldError -> new ExceptionsDto(fieldError.getField(), fieldError.getDefaultMessage()))
                .forEach(exceptionsDto::add);  // Adiciona os erros à lista exceptionsDto

        // Retorna os erros no corpo da resposta com o status 400
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseAllDto<>(exceptionsDto, "Validações falhou", 400));
    }
}
