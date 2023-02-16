package com.developerrafu.clientservice.controllers;

import com.developerrafu.clientservice.models.rest.responses.ClienteResponse;
import com.developerrafu.clientservice.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClientRestController {
    private final ClienteService service;

    @Autowired
    public ClientRestController(final ClienteService service) {
        this.service = service;
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteResponse> getByCpf(@PathVariable final String cpf) {
        return ResponseEntity.ok(service.getByCPF(cpf));
    }
}
