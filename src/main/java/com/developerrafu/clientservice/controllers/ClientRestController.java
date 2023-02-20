package com.developerrafu.clientservice.controllers;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.PATH;

import com.developerrafu.clientservice.exceptions.ClienteNotFoundException;
import com.developerrafu.clientservice.helpers.JsonUtils;
import com.developerrafu.clientservice.helpers.LogEnum;
import com.developerrafu.clientservice.models.rest.responses.ClienteResponse;
import com.developerrafu.clientservice.services.ClienteService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import nonapi.io.github.classgraph.json.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
@Slf4j
public class ClientRestController {
    private final ClienteService service;

    @Autowired
    public ClientRestController(final ClienteService service) {
        this.service = service;
    }

    @GetMapping(
            value = "/{cpf}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClienteResponse> getByCpf(
            @Parameter(
                    description = "CPF do usuário",
                    name = "cpf",
                    required = true,
                    example = "00000000000",
                    in = PATH)
            @PathVariable
            @Valid
            @Min(11) final String cpf) {
        log.info(LogEnum.GET_CLIENTE_REQUEST.getFormatteMessage(cpf));

        final var response = service.getByCPF(cpf).map(ResponseEntity::ok).orElseThrow(ClienteNotFoundException::new);

        log.info(LogEnum.GET_CLIENTE_RESPONSE.getFormatteMessage(JsonUtils.toString(response.getBody()), response.getStatusCode()));

        return response;
    }
}
