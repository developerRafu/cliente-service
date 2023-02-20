package com.developerrafu.clientservice.models.rest.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
public class ClienteResponse {
  private Long id;
  private String nome;
  private String cpf;
  private EnderecoResponse endereco;
}
