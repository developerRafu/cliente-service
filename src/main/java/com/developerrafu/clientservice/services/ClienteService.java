package com.developerrafu.clientservice.services;

import com.developerrafu.clientservice.exceptions.ClienteNotFoundException;
import com.developerrafu.clientservice.mappers.ClienteMapper;
import com.developerrafu.clientservice.models.domain.Cliente;
import com.developerrafu.clientservice.models.rest.responses.ClienteResponse;
import com.developerrafu.clientservice.repositories.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
  private final ClienteRepository repository;
  private final EnderecoService enderecoService;
  private final ClienteMapper mapper;

  @Autowired
  public ClienteService(
      final ClienteRepository repository,
      final EnderecoService enderecoService,
      final ClienteMapper mapper) {
    this.repository = repository;
    this.enderecoService = enderecoService;
    this.mapper = mapper;
  }

  @Transactional
  public Cliente save(final Cliente cliente) {
    return repository.save(cliente);
  }

  public ClienteResponse getByCPF(final String cpf) {
    final var result = this.repository.findByCpf(cpf);
    return result
        .map(
            cliente -> {
              final var endereco = this.enderecoService.findEnderecoByClienteId(cliente.getId());
              return mapper.toResponse(cliente, endereco);
            })
        .orElseThrow(ClienteNotFoundException::new);
  }
}
