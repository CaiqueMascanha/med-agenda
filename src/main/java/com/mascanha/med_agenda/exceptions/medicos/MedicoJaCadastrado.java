package com.mascanha.med_agenda.exceptions.medicos;


public class MedicoJaCadastrado extends RuntimeException {

    public MedicoJaCadastrado(String msg) {
        super(msg);
    }
}
