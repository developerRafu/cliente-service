package com.developerrafu.clientservice.controllers;

import com.developerrafu.clientservice.helpers.JsonUtils;
import com.developerrafu.clientservice.helpers.LogEnum;
import com.developerrafu.clientservice.models.rest.requests.EnderecoRequest;
import com.developerrafu.clientservice.models.rest.responses.EstadoIdResponse;
import com.developerrafu.clientservice.models.rest.responses.EstadoResponse;
import com.developerrafu.clientservice.models.rest.responses.MunicipioResponse;
import com.developerrafu.clientservice.models.rest.responses.ViaCepResponse;
import com.developerrafu.clientservice.services.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static io.swagger.v3.oas.annotations.enums.ParameterIn.PATH;

@RestController
@RequestMapping("/enderecos")
@Slf4j
public class EnderecoController {
    private final EnderecoService service;

    @Autowired
    public EnderecoController(final EnderecoService service) {
        this.service = service;
    }

    @Operation(summary = "Recuperar endereço", description = "Recuperar endereço por cep")
    @GetMapping(value = "/{cep}")
    public ResponseEntity<ViaCepResponse> getByCep(@PathVariable @Valid @Min(8) final String cep) {
        log.info(LogEnum.GET_ENDERECO_BY_CEP_REQUEST.getFormatteMessage(cep));

        final var response = ResponseEntity.ok(service.findCep(cep));

        log.info(LogEnum.GET_ENDERECO_BY_CEP_RESPONSE.getFormatteMessage(JsonUtils.toString(response.getBody()), response.getStatusCode()));

        return response;
    }

    @Operation(summary = "Salvar ou alterar", description = "Salvar ou alterar endereço")
    @PatchMapping
    public ResponseEntity<URI> upsert(@RequestBody @Valid @NotNull EnderecoRequest request) {
        log.info(LogEnum.UPSERT_ENDERECO_REQUEST.getFormatteMessage(JsonUtils.toString(request)));

        final var result = service.saveRequest(request);
        final var uri =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{cep}")
                        .buildAndExpand(result.getCep())
                        .toUri();
        final var response = ResponseEntity.status(HttpStatus.CREATED).body(uri);

        log.info(LogEnum.UPSERT_ENDERECO_RESPONSE.getFormatteMessage(JsonUtils.toString(response.getBody()), response.getStatusCode()));
        return response;
    }

    @Operation(summary = "Recuperar estados", description = "Recuperar todos os estados do Brasil")
    @GetMapping(value = "/estados")
    public ResponseEntity<List<EstadoIdResponse>> getEstados() {
        log.info(LogEnum.GET_ESTADOS_REQUEST.getFormatteMessage());

        final var result = service.getEstados();

        final var auxList =
                result.stream()
                        .filter(EnderecoController::isSPOrRJ)
                        .map(this::toEstadoIdResponse)
                        .sorted((e1, e2) -> e2.getNome().compareTo(e1.getNome()))
                        .collect(Collectors.toList());

        auxList.addAll(
                result.stream()
                        .map(this::toEstadoIdResponse)
                        .filter(
                                estado ->
                                        auxList.stream()
                                                .noneMatch(item -> Objects.equals(item.getId(), estado.getId())))
                        .sorted(Comparator.comparing(EstadoIdResponse::getNome))
                        .toList());

        final var response = ResponseEntity.ok(auxList);

        log.info(LogEnum.GET_ESTADOS_RESPONSE.getFormatteMessage(JsonUtils.toString(response.getBody()), response.getStatusCode()));

        return response;
    }

    @Operation(
            summary = "Recuperar municípios",
            description = "Recuperar todos os municípios por estado do Brasil")
    @GetMapping(value = "/municipios/{estadoId}")
    public ResponseEntity<List<MunicipioResponse>> getMunicipiosbByUf(
            @Parameter(
                    description = "Id do estado",
                    name = "estadoId",
                    required = true,
                    example = "1",
                    in = PATH)
            @PathVariable
            @Valid
            @Min(1) final Long estadoId) {

        log.info(LogEnum.GET_MUNICIPIOS_REQUEST.getFormatteMessage(estadoId));

        final var response = ResponseEntity.ok(service.getMunicipios(estadoId));

        log.info(LogEnum.GET_MUNICIPIOS_RESPONSE.getFormatteMessage(JsonUtils.toString(response.getBody()), response.getStatusCode()));
        return response;
    }

    private static boolean isSPOrRJ(final EstadoResponse estadoResponse) {
        return Objects.equals(estadoResponse.getSigla(), "SP")
                || Objects.equals(estadoResponse.getSigla(), "RJ");
    }

    private EstadoIdResponse toEstadoIdResponse(final EstadoResponse estadoResponse) {
        return EstadoIdResponse.builder()
                .id(estadoResponse.getId())
                .nome(estadoResponse.getNome())
                .build();
    }
}
