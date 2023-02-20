package com.developerrafu.clientservice.services;

import com.developerrafu.clientservice.models.rest.responses.EstadoResponse;
import com.developerrafu.clientservice.models.rest.responses.MunicipioResponse;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class LocalidadeService {
  private final LocalidadeClient client;

  public LocalidadeService(final LocalidadeClient cliente) {
    this.client = cliente;
  }

  public List<EstadoResponse> getEstados() {
    return client.getEstados();
  }

  public List<MunicipioResponse> getMunicipios(final Long estadoId) {
    return client.getMunicipios(estadoId);
  }
}
