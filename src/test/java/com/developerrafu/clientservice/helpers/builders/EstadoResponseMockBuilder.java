package com.developerrafu.clientservice.helpers.builders;

import com.developerrafu.clientservice.models.rest.responses.EstadoResponse;
import com.developerrafu.clientservice.helpers.ConstantsHelper;

public class EstadoResponseMockBuilder {
    private final EstadoResponse response;

    private EstadoResponseMockBuilder() {
        this.response = new EstadoResponse();
    }

    public static EstadoResponseMockBuilder getBuilder() {
        return new EstadoResponseMockBuilder();
    }

    public EstadoResponse build() {
        return response;
    }

    public EstadoResponseMockBuilder defaultValues() {
        response.setId(1L);
        response.setNome(ConstantsHelper.MOCKED_STATE_NAME);
        response.setSigla(ConstantsHelper.MOCKED_UF);
        return this;
    }

    public EstadoResponseMockBuilder withUF(final String uf) {
        response.setSigla(uf);
        response.setId((long) uf.hashCode());
        return this;
    }
}
