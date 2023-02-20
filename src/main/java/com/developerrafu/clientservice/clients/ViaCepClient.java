package com.developerrafu.clientservice.clients;

import com.developerrafu.clientservice.models.rest.responses.ViaCepResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "${services.endereco}", path = "/ws", name = "viacep-ws")
public interface ViaCepClient {
  @GetMapping("/{cep}/json")
  ViaCepResponse getCetp(@PathVariable final String cep);
}
