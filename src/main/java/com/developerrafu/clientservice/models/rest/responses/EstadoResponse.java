package com.developerrafu.clientservice.models.rest.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class EstadoResponse {
  private Long id;
  private String sigla;
  private String nome;
}
