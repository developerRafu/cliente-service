package com.developerrafu.clientservice.exceptions;

import lombok.Getter;

@Getter
public class CepNotFoundException extends RuntimeException {
    private final String cep;

    public CepNotFoundException(final String cep) {
        this.cep = cep;
    }
}
