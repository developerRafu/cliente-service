package com.developerrafu.clientservice.services;

import com.developerrafu.clientservice.clients.LocalidadeClient;
import com.developerrafu.clientservice.helpers.JsonUtils;
import com.developerrafu.clientservice.helpers.LogEnum;
import com.developerrafu.clientservice.models.rest.responses.EstadoResponse;
import com.developerrafu.clientservice.models.rest.responses.MunicipioResponse;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LocalidadeService {
  private final LocalidadeClient client;

  public LocalidadeService(final LocalidadeClient cliente) {
    this.client = cliente;
  }

  public List<EstadoResponse> getEstados() {
    final var response = client.getEstados();
    log.info(
        LogEnum.GET_ESTADOS_LOCALIDADE_SERVICE_RESPONSE.getFormatteMessage(
            JsonUtils.toString(response)));
    return response;
  }

  public List<MunicipioResponse> getMunicipios(final Long estadoId) {
    log.info(LogEnum.GET_MUNCIPIOS_LOCALIDADE_SERVICE_REQUEST.getFormatteMessage(estadoId));
    final var response = client.getMunicipios(estadoId);
    log.info(
        LogEnum.GET_MUNICIPIOS_LOCALIDADE_SERVICE_RESPONSE.getFormatteMessage(
            JsonUtils.toString(response)));
    return response;
  }
}
