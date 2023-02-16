package com.developerrafu.clientservice.services;

import com.developerrafu.clientservice.exceptions.CepNotFoundException;
import com.developerrafu.clientservice.helpers.CompletableFutureHelper;
import com.developerrafu.clientservice.mappers.EnderecoMapper;
import com.developerrafu.clientservice.models.domain.Endereco;
import com.developerrafu.clientservice.models.rest.requests.EnderecoRequest;
import com.developerrafu.clientservice.models.rest.responses.EnderecoResponse;
import com.developerrafu.clientservice.models.rest.responses.ViaCepResponse;
import com.developerrafu.clientservice.repositories.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class EnderecoService {
    private final EnderecoRepository repository;
    private final ViaCepClient client;
    private final EnderecoMapper mapper;

    @Autowired
    public EnderecoService(final EnderecoRepository repository, final ViaCepClient client, final EnderecoMapper mapper) {
        this.repository = repository;
        this.client = client;
        this.mapper = mapper;
    }

    public ViaCepResponse findCep(final String cep) {
        return this.client.getCetp(cep);
    }

    @Transactional
    public Endereco save(final Endereco endereco) {
        final var enderecoFound = this.repository.findByClienteId(endereco.getClienteId());
        enderecoFound.ifPresent(value -> endereco.setId(value.getId()));
        return this.repository.save(endereco);
    }

    public Endereco save(final EnderecoRequest request) {
        final var endereco = mapper.toEndereco(request);
        return save(endereco);
    }

    public EnderecoResponse findEnderecoByClienteId(final Long clienteId) {
        final var enderecoFound = this.repository.findByClienteId(clienteId);
        return enderecoFound.map(endereco -> {
            final var cepResponse = this.findCep(endereco.getCep());
            return mapper.toResponse(endereco, cepResponse);
        }).orElse(null);
    }
}
