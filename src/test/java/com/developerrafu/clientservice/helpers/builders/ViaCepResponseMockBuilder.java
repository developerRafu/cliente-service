package com.developerrafu.clientservice.helpers.builders;

import com.developerrafu.clientservice.helpers.ConstantsHelper;
import com.developerrafu.clientservice.models.rest.responses.ViaCepResponse;

public class ViaCepResponseMockBuilder {
  private final ViaCepResponse response;

  private ViaCepResponseMockBuilder() {
    this.response = new ViaCepResponse();
  }

  public static ViaCepResponseMockBuilder getBuilder() {
    return new ViaCepResponseMockBuilder();
  }

  public ViaCepResponse build() {
    return response;
  }

  public ViaCepResponseMockBuilder defaultValues() {
    response.setCep(ConstantsHelper.MOCKED_CEP);
    response.setLogradouro("Rua Vitorino Carmilo");
    response.setComplemento("");
    response.setBairro("Barra Funda");
    response.setLocalidade("São Paulo");
    response.setUf("SP");
    return this;
  }
}
