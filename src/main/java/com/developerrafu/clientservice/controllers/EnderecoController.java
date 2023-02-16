package com.developerrafu.clientservice.controllers;

import com.developerrafu.clientservice.models.rest.requests.EnderecoRequest;
import com.developerrafu.clientservice.models.rest.responses.EstadoResponse;
import com.developerrafu.clientservice.models.rest.responses.ViaCepResponse;
import com.developerrafu.clientservice.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public ResponseEntity<Void> upsert(@RequestBody EnderecoRequest request) {
        final var result = service.save(request);
        final var uri =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{cep}")
                        .buildAndExpand(result.getCep())
                        .toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/estados")
    public ResponseEntity<List<String>> getEstados() {
        final var result = service.getEstados();
        final var auxList = result
                .stream()
                .filter(EnderecoController::isSPOrRJ)
                .map(EstadoResponse::getNome)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        auxList.addAll(result.stream().map(EstadoResponse::getNome).filter(nome -> !auxList.contains(nome)).sorted(String::compareTo).toList());
        return ResponseEntity.ok(auxList);
    }

    private static boolean isSPOrRJ(final EstadoResponse estadoResponse) {
        return Objects.equals(estadoResponse.getSigla(), "SP") || Objects.equals(estadoResponse.getSigla(), "RJ");
    }
}
