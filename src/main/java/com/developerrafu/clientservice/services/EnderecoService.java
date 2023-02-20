package com.developerrafu.clientservice.services;

import com.developerrafu.clientservice.clients.ViaCepClient;
import com.developerrafu.clientservice.mappers.EnderecoMapper;
import com.developerrafu.clientservice.models.domain.Endereco;
import com.developerrafu.clientservice.models.rest.requests.EnderecoRequest;
import com.developerrafu.clientservice.models.rest.responses.EnderecoResponse;
import com.developerrafu.clientservice.models.rest.responses.EstadoResponse;
import com.developerrafu.clientservice.models.rest.responses.MunicipioResponse;
import com.developerrafu.clientservice.models.rest.responses.ViaCepResponse;
import com.developerrafu.clientservice.repositories.EnderecoRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {
  private final EnderecoRepository repository;
  private final EnderecoMapper mapper;
  private final LocalidadeService localidadeService;
  private final ViaCepService viaCepService;

  @Autowired
  public EnderecoService(
          final EnderecoRepository repository,
          final EnderecoMapper mapper,
          final LocalidadeService localidadeService, final ViaCepService viaCepService) {
    this.repository = repository;
    this.viaCepService = viaCepService;
    this.mapper = mapper;
    this.localidadeService = localidadeService;
  }

  public ViaCepResponse findCep(final String cep) {
    return this.viaCepService.getCetp(cep);
  }

  @Transactional
  public Endereco save(final Endereco endereco) {
    final var enderecoFound = this.repository.findByClienteId(endereco.getClienteId());
    enderecoFound.ifPresent(value -> endereco.setId(value.getId()));
    return this.repository.save(endereco);
  }

  public Endereco saveRequest(final EnderecoRequest request) {
    final var endereco = mapper.toEndereco(request);
    return save(endereco);
  }

  public EnderecoResponse findEnderecoByClienteId(final Long clienteId) {
    final var enderecoFound = this.repository.findByClienteId(clienteId);
    return enderecoFound
        .map(
            endereco -> {
              final var cepResponse = this.findCep(endereco.getCep());
              return mapper.toResponse(endereco, cepResponse);
            })
        .orElse(null);
  }

  public List<EstadoResponse> getEstados() {
    return localidadeService.getEstados();
  }

  public List<MunicipioResponse> getMunicipios(final Long estadoId) {
    return localidadeService.getMunicipios(estadoId);
  }
}
