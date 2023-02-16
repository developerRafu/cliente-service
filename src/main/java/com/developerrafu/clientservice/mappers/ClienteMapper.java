package com.developerrafu.clientservice.mappers;

import com.developerrafu.clientservice.models.domain.Cliente;
import com.developerrafu.clientservice.models.rest.responses.ClienteResponse;
import com.developerrafu.clientservice.models.rest.responses.EnderecoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
  ClienteResponse toResponse(final Cliente cliente);

  default ClienteResponse toResponse(final Cliente cliente, final EnderecoResponse endereco) {
    final var response = toResponse(cliente);
    if (endereco != null) {
      response.setEndereco(endereco);
    }
    return response;
  }
}
