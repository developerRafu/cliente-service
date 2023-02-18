package com.developerrafu.clientservice.helpers.builders;

import com.developerrafu.clientservice.helpers.ConstantsHelper;
import com.developerrafu.clientservice.models.rest.responses.EnderecoResponse;

public class EnderecoResponseMockBuilder {
    private final EnderecoResponse response;

    private EnderecoResponseMockBuilder() {
        this.response = new EnderecoResponse();
    }

    public static EnderecoResponseMockBuilder getBuilder() {
        return new EnderecoResponseMockBuilder();
    }

    public EnderecoResponse build() {
        return response;
    }

    public EnderecoResponseMockBuilder defaultValues(){
        response.setId(1L);
        response.setCep(ConstantsHelper.MOCKED_CEP);
        response.setNumero("22");
        response.setLogradouro("Rua Vitorino Carmilo");
        response.setComplemento("");
        response.setBairro("Barra Funda");
        response.setLocalidade("São Paulo");
        response.setUf("SP");
        return this;
    }
}
