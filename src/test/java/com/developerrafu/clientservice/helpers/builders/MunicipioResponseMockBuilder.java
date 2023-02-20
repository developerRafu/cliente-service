package com.developerrafu.clientservice.helpers.builders;

import com.developerrafu.clientservice.helpers.ConstantsHelper;
import com.developerrafu.clientservice.models.rest.responses.MunicipioResponse;

public class MunicipioResponseMockBuilder {
    private final MunicipioResponse MunicipioResponse;

    private MunicipioResponseMockBuilder() {
        this.MunicipioResponse = new MunicipioResponse();
    }

    public static MunicipioResponseMockBuilder getBuilder() {
        return new MunicipioResponseMockBuilder();
    }

    public MunicipioResponse build() {
        return MunicipioResponse;
    }

    public MunicipioResponseMockBuilder defaultValues() {
        MunicipioResponse.setId(1L);
        MunicipioResponse.setNome(ConstantsHelper.MOCKED_STATE_NAME);
        return this;
    }
}
