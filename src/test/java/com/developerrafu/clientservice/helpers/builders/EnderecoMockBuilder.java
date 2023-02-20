package com.developerrafu.clientservice.helpers.builders;

import com.developerrafu.clientservice.helpers.ConstantsHelper;
import com.developerrafu.clientservice.models.domain.Endereco;

public class EnderecoMockBuilder {
    private final Endereco response;

    private EnderecoMockBuilder() {
        this.response = new Endereco();
    }

    public static EnderecoMockBuilder getBuilder() {
        return new EnderecoMockBuilder();
    }

    public Endereco build() {
        return response;
    }

    public EnderecoMockBuilder defaultValues(){
        response.setId(1L);
        response.setCep(ConstantsHelper.MOCKED_CEP);
        response.setNumero("22");
        return this;
    }

    public EnderecoMockBuilder withClienteId(final long clienteId) {
        response.setClienteId(clienteId);
        return this;
    }
}
