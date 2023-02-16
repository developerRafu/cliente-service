package com.developerrafu.clientservice.models.rest.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoResponse {
    private String id;
    private String sigla;
    private String nome;
    private RegiaoResponse regiao;
}
