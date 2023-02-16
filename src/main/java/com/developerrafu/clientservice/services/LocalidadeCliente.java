package com.developerrafu.clientservice.services;

import com.developerrafu.clientservice.models.rest.responses.EstadoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
        url = "${services.localidade}",
        path = "/localidades",
        name = "localidades-api"
)
public interface LocalidadeCliente {
    @GetMapping("/estados/")
    List<EstadoResponse> getEstados();
}
