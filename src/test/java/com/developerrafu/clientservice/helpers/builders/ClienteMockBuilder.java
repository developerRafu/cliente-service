package com.developerrafu.clientservice.helpers.builders;

import com.developerrafu.clientservice.helpers.ConstantsHelper;
import com.developerrafu.clientservice.models.domain.Cliente;

public class ClienteMockBuilder {
  private final Cliente cliente;

  private ClienteMockBuilder() {
    this.cliente = new Cliente();
  }

  public static ClienteMockBuilder getBuilder() {
    return new ClienteMockBuilder();
  }

  public Cliente build() {
    return cliente;
  }

  public ClienteMockBuilder defaultValues() {
    cliente.setId(1L);
    cliente.setNome(ConstantsHelper.MOCKED_NAME);
    cliente.setCpf(ConstantsHelper.MOCKED_CPF);
    return this;
  }
}
