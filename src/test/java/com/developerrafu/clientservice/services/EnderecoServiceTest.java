package com.developerrafu.clientservice.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.developerrafu.clientservice.clients.ViaCepClient;
import com.developerrafu.clientservice.helpers.builders.*;
import com.developerrafu.clientservice.mappers.EnderecoMapper;
import com.developerrafu.clientservice.models.rest.requests.EnderecoRequest;
import com.developerrafu.clientservice.repositories.EnderecoRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class EnderecoServiceTest {
  EnderecoRepository repository;
  ViaCepClient client;
  EnderecoMapper mapper;
  LocalidadeService localidadeService;
  EnderecoService service;

  @BeforeEach
  void setUp() {
    repository = mock(EnderecoRepository.class);
    client = mock(ViaCepClient.class);
    localidadeService = mock(LocalidadeService.class);
    mapper = Mappers.getMapper(EnderecoMapper.class);
    service = new EnderecoService(repository, client, mapper, localidadeService, viaCepService);
  }

  @Test
  void shouldSaveEndereco() {
    final var endereco = EnderecoMockBuilder.getBuilder().defaultValues().build();
    when(repository.save(any())).thenReturn(endereco);
    final var result = service.save(endereco);
    assertNotNull(result);
    assertEquals(endereco, result);
  }

  @Test
  void shouldSaveEnderecoRequest() {
    final EnderecoRequest endereco =
        EnderecoRequestMockBuilder.getBuilder().defaultValues().build();
    when(repository.save(any()))
        .thenReturn(EnderecoMockBuilder.getBuilder().defaultValues().withClienteId(1L).build());
    final var result = service.saveRequest(endereco);
    assertNotNull(result);
  }

  @Test
  void shouldUpdateWhenClienteIdExists() {
    final var endereco = EnderecoMockBuilder.getBuilder().defaultValues().withClienteId(1L).build();
    when(repository.save(any())).thenReturn(endereco);
    when(repository.findByClienteId(any())).thenReturn(Optional.of(endereco));
    final var result = service.save(endereco);
    assertNotNull(result);
    assertEquals(endereco, result);
  }

  @Test
  void shouldReturnEnderecoResponse() {
    when(repository.findByClienteId(any()))
        .thenReturn(
            Optional.of(
                EnderecoMockBuilder.getBuilder().defaultValues().withClienteId(1L).build()));
    when(client.getCetp(any()))
        .thenReturn(ViaCepResponseMockBuilder.getBuilder().defaultValues().build());
    final var result = service.findEnderecoByClienteId(1L);
    assertNotNull(result);
    assertNotNull(result.getNumero());
    assertNotNull(result.getId());
    assertNotNull(result.getCep());
    assertNotNull(result.getUf());
    assertNotNull(result.getBairro());
    assertNotNull(result.getComplemento());
    assertNotNull(result.getLocalidade());
    assertNotNull(result.getLogradouro());
  }

  @Test
  void shouldReturnNull() {
    final var result = service.findEnderecoByClienteId(1L);
    assertNull(result);
  }

  @Test
  void shouldReturnEstados() {
    when(localidadeService.getEstados())
        .thenReturn(List.of(EstadoResponseMockBuilder.getBuilder().defaultValues().build()));
    final var result = service.getEstados();
    assertNotNull(result);
    assertNotEquals(Collections.emptyList(), result);
  }

  @Test
  void shoulReturnMunicipios() {
    when(localidadeService.getMunicipios(any()))
        .thenReturn(List.of(MunicipioResponseMockBuilder.getBuilder().defaultValues().build()));
    final var result = service.getMunicipios(1L);
    assertNotNull(result);
    assertNotEquals(Collections.emptyList(), result);
  }
}
