package com.mascanha.med_agenda.exceptions.medicos;

public class SemDadosMedicos extends RuntimeException{
    public SemDadosMedicos(String msg) {
        super(msg);
    }
}
