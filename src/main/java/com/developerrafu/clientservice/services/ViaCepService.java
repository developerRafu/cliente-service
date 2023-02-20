package com.developerrafu.clientservice.services;

import com.developerrafu.clientservice.clients.ViaCepClient;
import com.developerrafu.clientservice.helpers.LogEnum;
import com.developerrafu.clientservice.models.rest.responses.ViaCepResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ViaCepService {
  private final ViaCepClient client;

  @Autowired
  public ViaCepService(final ViaCepClient client) {
    this.client = client;
  }

  public ViaCepResponse getCetp(final String cep) {
    log.info(LogEnum.GET_VIA_CEP_REQUEST.getFormatteMessage(cep));
    final var response = client.getCetp(cep);
    log.info(LogEnum.GET_VIA_CEP_RESPONSE.getFormatteMessage(cep));
    return response;
  }
}
