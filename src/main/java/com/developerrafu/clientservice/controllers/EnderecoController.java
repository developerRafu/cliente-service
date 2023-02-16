package com.developerrafu.clientservice.controllers;

import com.developerrafu.clientservice.models.rest.requests.EnderecoRequest;
import com.developerrafu.clientservice.models.rest.responses.ViaCepResponse;
import com.developerrafu.clientservice.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Void> upsert(@RequestBody EnderecoRequest request) {
        final var result = service.save(request);
        final var uri =
                ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{cep}")
                        .buildAndExpand(result.getCep())
                        .toUri();
        return ResponseEntity.created(uri).build();
    }
}
