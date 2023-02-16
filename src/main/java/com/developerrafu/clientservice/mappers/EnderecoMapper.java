package com.developerrafu.clientservice.mappers;

import com.developerrafu.clientservice.models.domain.Endereco;
import com.developerrafu.clientservice.models.rest.requests.EnderecoRequest;
import com.developerrafu.clientservice.models.rest.responses.EnderecoResponse;
import com.developerrafu.clientservice.models.rest.responses.ViaCepResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {
    default EnderecoResponse toResponse(final Endereco endereco, final ViaCepResponse viaCepResponse) {
        final var response = new EnderecoResponse();
        if (endereco != null) {
            response.setId(endereco.getId());
            response.setNumero(endereco.getNumero());
        }
        if (viaCepResponse != null) {
            response.setCep(viaCepResponse.getCep());
            response.setLogradouro(viaCepResponse.getLogradouro());
            response.setComplemento(viaCepResponse.getComplemento());
            response.setBairro(viaCepResponse.getBairro());
            response.setLocalidade(viaCepResponse.getLocalidade());
            response.setUf(viaCepResponse.getUf());
        }
        return response;
    }

    Endereco toEndereco(EnderecoRequest request);
}
