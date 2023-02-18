package com.developerrafu.clientservice.helpers.builders;

import com.developerrafu.clientservice.helpers.ConstantsHelper;
import com.developerrafu.clientservice.models.rest.responses.ClienteResponse;

public class ClienteResponseMockBuilder {
    private final ClienteResponse response;

    private ClienteResponseMockBuilder() {
        this.response = new ClienteResponse();
    }

    public static ClienteResponseMockBuilder getBuilder() {
        return new ClienteResponseMockBuilder();
    }

    public ClienteResponse build() {
        return response;
    }

    public ClienteResponseMockBuilder defaultValues(){
        response.setId(1L);
        response.setNome(ConstantsHelper.MOCKED_NAME);
        response.setCpf(ConstantsHelper.MOCKED_CPF);
        response.setEndereco(EnderecoResponseMockBuilder.getBuilder().defaultValues().build());
        return this;
    }
}
