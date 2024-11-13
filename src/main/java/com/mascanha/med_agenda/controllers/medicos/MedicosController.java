package com.mascanha.med_agenda.controllers.medicos;

import com.mascanha.med_agenda.dto.ResponseAllDto;
import com.mascanha.med_agenda.dto.medicos.requests.RequestMedicoDto;
import com.mascanha.med_agenda.dto.medicos.responses.ResponseMedicoDto;
import com.mascanha.med_agenda.services.medicos.CreatedMedicosService;
import com.mascanha.med_agenda.services.medicos.DeleteMedicoService;
import com.mascanha.med_agenda.services.medicos.GetAllMedicosService;
import com.mascanha.med_agenda.services.medicos.UpdateMedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicosController {

    @Autowired
    private CreatedMedicosService createdMedicosService;

    @Autowired
    private GetAllMedicosService getAllMedicosService;

    @Autowired
    private DeleteMedicoService deleteMedicoService;

    @Autowired
    private UpdateMedicoService updateMedicoService;

    @PostMapping
    public ResponseEntity<ResponseAllDto<ResponseMedicoDto>> criar(@RequestBody RequestMedicoDto requestMedicoDto) {
        // Chama o serviço para criar o médico e retorna o ResponseAllDto pronto
        ResponseAllDto<ResponseMedicoDto> response = createdMedicosService.execute(requestMedicoDto);

        // Retorna o ResponseEntity com o corpo contendo ResponseAllDto
        return ResponseEntity.created(null).body(response);
    }

    @GetMapping
    public ResponseEntity<ResponseAllDto<List<ResponseMedicoDto>>> obterTodosMedicos() {
        ResponseAllDto<List<ResponseMedicoDto>> response = getAllMedicosService.execute();

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseAllDto> deletarMedico(@PathVariable Long id) {
        ResponseAllDto response = deleteMedicoService.execute(id);

        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseAllDto<ResponseMedicoDto>> atualizarMedico(@PathVariable Long id, @Valid @RequestBody RequestMedicoDto requestMedicoDto) {
        ResponseAllDto<ResponseMedicoDto> response = updateMedicoService.execute(id, requestMedicoDto);

        return ResponseEntity.ok().body(response);
    }
}
