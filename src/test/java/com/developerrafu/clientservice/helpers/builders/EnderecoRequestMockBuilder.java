package com.developerrafu.clientservice.helpers.builders;

import com.developerrafu.clientservice.helpers.ConstantsHelper;
import com.developerrafu.clientservice.models.rest.requests.EnderecoRequest;

public class EnderecoRequestMockBuilder {
  private final EnderecoRequest request;

  private EnderecoRequestMockBuilder() {
    this.request = new EnderecoRequest();
  }

  public static EnderecoRequestMockBuilder getBuilder() {
    return new EnderecoRequestMockBuilder();
  }

  public EnderecoRequest build() {
    return request;
  }

  public EnderecoRequestMockBuilder defaultValues() {
    request.setCep(ConstantsHelper.MOCKED_CEP);
    request.setNumero("22");
    request.setClienteId(1L);
    return this;
  }

  public EnderecoRequestMockBuilder withClienteId(final long clienteId) {
    request.setClienteId(clienteId);
    return this;
  }
}
