package com.developerrafu.clientservice.clients;

import com.developerrafu.clientservice.models.rest.responses.EstadoResponse;
import com.developerrafu.clientservice.models.rest.responses.MunicipioResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "${services.localidade}", path = "/localidades", name = "localidades-api")
public interface LocalidadeClient {
  @GetMapping(value = "/estados/", consumes = "application/json")
  List<EstadoResponse> getEstados();

  @GetMapping(value = "estados/{estadoId}/municipios", consumes = "application/json")
  List<MunicipioResponse> getMunicipios(@PathVariable final Long estadoId);
}
