package com.developerrafu.clientservice.controllers;

import com.developerrafu.clientservice.models.rest.requests.EnderecoRequest;
import com.developerrafu.clientservice.models.rest.responses.EstadoIdResponse;
import com.developerrafu.clientservice.models.rest.responses.EstadoResponse;
import com.developerrafu.clientservice.models.rest.responses.MunicipioResponse;
import com.developerrafu.clientservice.models.rest.responses.ViaCepResponse;
import com.developerrafu.clientservice.services.EnderecoService;
import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
  private final EnderecoService service;

  @Autowired
  public EnderecoController(final EnderecoService service) {
    this.service = service;
  }

  @GetMapping("/{cep}")
  public ResponseEntity<ViaCepResponse> getByCep(@PathVariable final String cep) {
    return ResponseEntity.ok(service.findCep(cep));
  }

  @PatchMapping
  public ResponseEntity<URI> upsert(@RequestBody EnderecoRequest request) {
    final var result = service.save(request);
    final var uri =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{cep}")
            .buildAndExpand(result.getCep())
            .toUri();
    return ResponseEntity.status(HttpStatus.CREATED).body(uri);
  }

  @GetMapping("/estados")
  public ResponseEntity<List<EstadoIdResponse>> getEstados() {
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

    return ResponseEntity.ok(auxList);
  }

  private EstadoIdResponse toEstadoIdResponse(final EstadoResponse estadoResponse) {
    return EstadoIdResponse.builder()
        .id(estadoResponse.getId())
        .nome(estadoResponse.getNome())
        .build();
  }

  @GetMapping("/municipios/{estadoId}")
  public ResponseEntity<List<MunicipioResponse>> getMunicipiosbByUf(
      @PathVariable final Long estadoId) {
    return ResponseEntity.ok(service.getMunicipios(estadoId));
  }

  private static boolean isSPOrRJ(final EstadoResponse estadoResponse) {
    return Objects.equals(estadoResponse.getSigla(), "SP")
        || Objects.equals(estadoResponse.getSigla(), "RJ");
  }
}
