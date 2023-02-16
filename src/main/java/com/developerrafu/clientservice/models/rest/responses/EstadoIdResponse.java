package com.developerrafu.clientservice.models.rest.responses;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstadoIdResponse {
  private String nome;
  private Long id;
}
