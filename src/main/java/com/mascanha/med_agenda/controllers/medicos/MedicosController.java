package com.mascanha.med_agenda.controllers.medicos;

import com.mascanha.med_agenda.dto.medicos.requests.RequestMedicoDto;
import com.mascanha.med_agenda.dto.medicos.responses.ResponseMedicoDto;
import com.mascanha.med_agenda.services.medicos.CreatedMedicosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicosController {

    @Autowired
    private CreatedMedicosService createdMedicosService;

    @PostMapping
    public ResponseEntity<ResponseMedicoDto> Criar(@RequestBody RequestMedicoDto requestMedicoDto) {
        ResponseMedicoDto result = createdMedicosService.execute(requestMedicoDto);

        return ResponseEntity.created(null).body(result);
    }
}
