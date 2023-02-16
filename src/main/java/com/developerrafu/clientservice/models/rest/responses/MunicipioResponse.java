package com.developerrafu.clientservice.models.rest.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MunicipioResponse {
  @JsonProperty private Long id;
  @JsonProperty private String nome;
}
