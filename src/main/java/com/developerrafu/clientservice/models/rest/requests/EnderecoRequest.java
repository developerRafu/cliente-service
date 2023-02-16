package com.developerrafu.clientservice.models.rest.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoRequest {
  private Long clienteId;
  private String numero;
  private String cep;
}
