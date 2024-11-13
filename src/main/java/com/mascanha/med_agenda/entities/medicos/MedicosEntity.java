package com.mascanha.med_agenda.entities.medicos;

import com.mascanha.med_agenda.dto.medicos.requests.RequestMedicoDto;
import com.mascanha.med_agenda.dto.medicos.responses.ResponseMedicoDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "medicos")
@Data
public class MedicosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String especialidade;
    private String crm;
    private String telefone;
    private String email;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updateAt;

    public static MedicosEntity fromDto(RequestMedicoDto medicoDto) {
        MedicosEntity medicosEntity = new MedicosEntity();

        medicosEntity.setNome(medicoDto.nome());
        medicosEntity.setEspecialidade(medicoDto.especialidade());
        medicosEntity.setCrm(medicoDto.crm());
        medicosEntity.setTelefone(medicoDto.telefone());
        medicosEntity.setEmail(medicoDto.email());

        return medicosEntity;

    }

    public ResponseMedicoDto toDto() {
        return new ResponseMedicoDto(
                this.getNome(),
                this.getEspecialidade(),
                this.getCrm(),
                this.getTelefone(),
                this.getEmail(),
                this.getCreatedAt()
        );
    }
}
